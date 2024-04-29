package switchfully.com.eurder.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Name {
@NotBlank(message = "must be provided")
    @Column(name = "first_name")
    private String firstName;
@NotBlank(message = "must be provided")
    @Column(name = "last_name")
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
