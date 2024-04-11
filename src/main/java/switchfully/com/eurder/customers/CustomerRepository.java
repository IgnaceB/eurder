package switchfully.com.eurder.customers;

import org.springframework.stereotype.Repository;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public class CustomerRepository {
    List<Customer> customers;

    public CustomerRepository() {
        this.customers=newArrayList();
    }

    public CustomerRepository(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer createCustomer(CustomerCreateDTO customerCreateDTO) {
        Customer newCustomer = new Customer(customerCreateDTO.getFirstName(),
                customerCreateDTO.getLastName(),
                customerCreateDTO.getAddress(),
                customerCreateDTO.getEmailAddress(),
                customerCreateDTO.getPhoneNumber());
        this.customers.add(newCustomer);
        return newCustomer;
    }
}
