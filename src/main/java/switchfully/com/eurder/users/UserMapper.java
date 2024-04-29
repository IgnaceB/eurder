package switchfully.com.eurder.users;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;

import java.util.UUID;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        return new UserDTO(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                user.getEmailAddress(),
                user.getPhoneNumber());
    }
    public User createToUser(UserCreateDTO userCreateDTO, Role role){
        return new User(UUID.randomUUID(),
                userCreateDTO.getName(),
                userCreateDTO.getEmailAddress(),
                userCreateDTO.getAddress(),
                userCreateDTO.getPhoneNumber(),
                userCreateDTO.getPassword(),
                role);
    }
}
