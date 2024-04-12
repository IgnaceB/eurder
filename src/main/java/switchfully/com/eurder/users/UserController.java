package switchfully.com.eurder.users;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import switchfully.com.eurder.security.Feature;
import switchfully.com.eurder.security.SecurityService;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/customers")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @PostMapping (produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createCustomer(@Valid @RequestBody UserCreateDTO userCreateDTO){
        return userService.createCustomer(userCreateDTO);
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllCustomers(@RequestHeader(value = "Authorization") String auth){
        securityService.verifyAuthorization(auth, Feature.VIEW_ALL_CUSTOMERS);
        return userService.getAllCustomers();
    }

    @GetMapping(path = "/{customerId}",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getOneCustomerById(@RequestHeader(value = "Authorization") String auth,@PathVariable UUID customerId){
        securityService.verifyAuthorization(auth, Feature.VIEW_ONE_CUSTOMER);
        return userService.getOneCustomerByID(customerId);
    }

}
