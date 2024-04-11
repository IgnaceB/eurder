package switchfully.com.eurder.customers;

import org.springframework.stereotype.Repository;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public class CustomerRepository {
    private List<Customer> customers;

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

    public List<Customer> getAllCustomers() {
        return this.customers;
    }

    public Optional<Customer> getOneCustomerById(UUID customerId) {
        return this.customers.stream()
                .filter(customer -> customer.getId()==customerId)
                .findFirst();
    }
}
