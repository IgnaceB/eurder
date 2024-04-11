package switchfully.com.eurder.customers.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerCreateDTO {
    @NotBlank(message = "First name must be provided")
    private String firstName;
    @NotBlank(message = "Last name must be provided")
    private String lastName;
    @NotBlank(message = "address must be provided")
    @Size(min=5, max=100,message = "address must have at least 5 letters, maximum 100 letters")
    private String address;
    @NotBlank(message="Email must be provided")
    @Email(message = "Please provide a correct email address")
    private String emailAddress;
    @NotBlank(message = "Phone number must be provided")
    @Size(min=7,max=15,message = "Phone number must be between 7 and 15 numbers")
    @Positive(message = "Phone number must be a positive number")
    private String phoneNumber;

    public CustomerCreateDTO(String firstName, String lastName, String address, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
}
