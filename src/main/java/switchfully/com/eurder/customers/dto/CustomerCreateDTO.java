package switchfully.com.eurder.customers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerCreateDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Size(min=5, max=100)
    private String address;
    @Email
    private String emailAddress;
    @Size(min=7,max=15)
    private String phoneNumber;

    public CustomerCreateDTO(String firstName, String lastName, String address, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }


}
