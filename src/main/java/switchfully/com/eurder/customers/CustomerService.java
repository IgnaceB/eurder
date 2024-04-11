package switchfully.com.eurder.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService( CustomerRepository customerRepository,CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
        return customerMapper.ToDTO(customerRepository.createCustomer(customerCreateDTO));
    }
}
