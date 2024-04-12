package switchfully.com.eurder.users;

import lombok.Data;
import switchfully.com.eurder.security.Feature;
import switchfully.com.eurder.security.Role;

import java.util.UUID;
@Data
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String phoneNumber;
    private String password;
    private Role role;

    public User(String firstName, String lastName, String emailAddress, String address, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = UUID.randomUUID();
        this.password=password;
        this.role=Role.CUSTOMER;
    }

    protected User(UUID id, String firstName, String lastName, String emailAddress, String address, String phoneNumber, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public boolean canHaveAccessTo(Feature feature) {
        return this.role.isAllowedToFeature(feature);
    }
}
