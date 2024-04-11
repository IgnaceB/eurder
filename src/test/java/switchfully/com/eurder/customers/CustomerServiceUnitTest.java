package switchfully.com.eurder.customers;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

@ExtendWith(MockitoExtension.class)
class CustomerServiceUnitTest {
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;

    @Test
    public void createCustomer_givenCustomerCreateDTO_thenReturnNewCustomerDTO(){
        //Given
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789");
        Customer customer = new Customer(customerCreateDTO.getFirstName(),customerCreateDTO.getLastName(),customerCreateDTO.getAddress(),customerCreateDTO.getEmailAddress(),customerCreateDTO.getPhoneNumber());
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getEmailAddress(),customer.getPhoneNumber());

        Mockito.when(customerRepository.createCustomer(customerCreateDTO)).thenReturn(customer);
        Mockito.when(customerMapper.ToDTO(customer)).thenReturn(customerDTO);

        Assertions.assertThat(customerService.createCustomer(customerCreateDTO)).isEqualTo(customerDTO);
    }
}