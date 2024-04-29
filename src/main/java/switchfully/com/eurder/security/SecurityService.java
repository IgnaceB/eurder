package switchfully.com.eurder.security;

import switchfully.com.eurder.exceptions.UnauthorizedException;
import switchfully.com.eurder.exceptions.WrongPasswordException;
import switchfully.com.eurder.users.User;
import switchfully.com.eurder.users.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import switchfully.com.eurder.exceptions.CustomerNotFoundException;

import java.util.Base64;
import java.util.UUID;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    @Autowired
    private final UserRepository userRepository;


    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void verifyAuthorization(String authorization, Feature feature) {
        IdPassword idPassword = getIdPassword(authorization);
        User user = userRepository
                .findById(idPassword.getUserId())
                .orElseThrow(()->new CustomerNotFoundException("Wrong ID"));

        if (!user.getPassword().equals(idPassword.getPassword())) {
            logger.error("Password does not match for user " + idPassword.getUserId());
            throw new WrongPasswordException();
        }
        if (!user.canHaveAccessTo(feature)) {
            logger.error("User " + idPassword.getUserId() + " does not have access to " + feature);
            throw new UnauthorizedException();
        }
    }

    public IdPassword getIdPassword(String authorization) {
        if (!authorization.contains("Basic ")) {
            logger.warn("User tried to log using the wrong authorization protocol");
            throw new IllegalArgumentException("Authorization is not valid");
        }
        String decodedAuthorization = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String idUser = decodedAuthorization.substring(0, decodedAuthorization.indexOf(":"));
        String password = decodedAuthorization.substring(decodedAuthorization.indexOf(":") + 1);
        return new IdPassword(UUID.fromString(idUser), password);
    }
}
