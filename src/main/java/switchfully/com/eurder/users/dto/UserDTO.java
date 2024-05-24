package switchfully.com.eurder.users.dto;

import lombok.Data;
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.utils.Name;

import java.util.UUID;
@Data
public class UserDTO {
    private UUID id;
    private Name name;
    private String emailAddress;
    private String address;
    private String phoneNumber;
    private Role role;


    public UserDTO(UUID id, Name name, String emailAddress, String address, String phoneNumber, Role role) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role=role;
    }

}
