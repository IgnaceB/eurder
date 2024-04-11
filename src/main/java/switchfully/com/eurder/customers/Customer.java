package switchfully.com.eurder.customers;

import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
}
