package switchfully.com.eurder.customers;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.customers.dto.CustomerDTO;

@Component
public class CustomerMapper {
    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getEmailAddress(),
                customer.getPhoneNumber());
    }
}
