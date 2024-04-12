package switchfully.com.eurder.users.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String phoneNumber;

    public UserDTO(UUID id, String firstName, String lastName, String emailAddress, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
