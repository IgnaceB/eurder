package switchfully.com.eurder.customers;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;
import switchfully.com.eurder.exceptions.CustomerNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService( CustomerRepository customerRepository,CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
        return customerMapper.toDTO(customerRepository.createCustomer(customerCreateDTO));
    }

    public List<CustomerDTO> getAllCustomers() {
    return customerRepository.getAllCustomers().stream()
            .map(customerMapper::toDTO)
            .collect(Collectors.toList());
    }

    public CustomerDTO getOneCustomerByID(UUID customerId) {
        return customerMapper.toDTO(
                customerRepository.getOneCustomerById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Can't find any customer with this ID")));


    }
}
