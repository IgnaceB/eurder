package switchfully.com.eurder.customers;

import lombok.Data;

import java.util.UUID;
@Data
public class Customer {
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String phoneNumber;

    public Customer(String firstName, String lastName, String emailAddress, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = UUID.randomUUID();
    }


}
