package switchfully.com.eurder.customers;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

public class CustomerServiceIntegrationTest {

    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    public void Setup(){
        customerService=new CustomerService(new CustomerRepository(),new CustomerMapper());
    }

    @Test
    public void createCustomer_givenCustomerCreateDTOIsFull_thenReturnNewCustomerDTO(){
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789");
        CustomerDTO customerDTO = customerService.createCustomer(customerCreateDTO);
        Assertions.assertThat(customerDTO.getFirstName()).isEqualTo(customerCreateDTO.getFirstName());
        Assertions.assertThat(customerDTO.getLastName()).isEqualTo(customerCreateDTO.getLastName());
        Assertions.assertThat(customerDTO.getAddress()).isEqualTo(customerCreateDTO.getAddress());
        Assertions.assertThat(customerDTO.getEmailAddress()).isEqualTo(customerCreateDTO.getEmailAddress());
        Assertions.assertThat(customerDTO.getPhoneNumber()).isEqualTo(customerCreateDTO.getPhoneNumber());
    }
}
