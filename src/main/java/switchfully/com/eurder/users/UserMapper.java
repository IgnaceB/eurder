package switchfully.com.eurder.users;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.users.dto.UserDTO;

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
}
