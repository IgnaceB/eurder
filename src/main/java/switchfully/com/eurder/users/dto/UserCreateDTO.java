package switchfully.com.eurder.users.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import switchfully.com.eurder.utils.Name;

@Data
public class UserCreateDTO {
    @NotNull()
    private @Valid Name name;
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
    @NotBlank(message = "password must be provided")
    private String password;


    public UserCreateDTO(switchfully.com.eurder.utils.Name name, String address, String emailAddress, String phoneNumber, String password) {
        this.name = name;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
