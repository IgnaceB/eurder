package switchfully.com.eurder.users;

import jakarta.persistence.*;
import lombok.Data;
import switchfully.com.eurder.security.Feature;
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.utils.Name;

import java.util.UUID;
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private UUID id;
/*    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    */
    @Embedded
    private Name name;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "address")
    private String address;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="password")
    private String password;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

/*    public User(String firstName, String lastName, String emailAddress, String address, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = UUID.randomUUID();
        this.password=password;
        this.role=Role.CUSTOMER;
    }*/

    public User(UUID id, Name name, String emailAddress, String address, String phoneNumber, String password, Role role) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return this.name.getFirstName();
    }

    public String getLastName() {
        return this.name.getLastName();
    }

    public Name getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean canHaveAccessTo(Feature feature) {
        return this.role.isAllowedToFeature(feature);
    }
}
