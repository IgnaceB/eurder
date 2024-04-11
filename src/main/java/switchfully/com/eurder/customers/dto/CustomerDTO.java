package switchfully.com.eurder.customers.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class CustomerDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String phoneNumber;

    public CustomerDTO(UUID id, String firstName, String lastName, String emailAddress, String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}
