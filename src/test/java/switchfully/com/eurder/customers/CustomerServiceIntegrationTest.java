package switchfully.com.eurder.customers;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

import static org.assertj.core.util.Lists.newArrayList;

public class CustomerServiceIntegrationTest {


    private CustomerService customerService;

    @BeforeEach
    public void Setup(){
        customerService=new CustomerService(new CustomerRepository(),new CustomerMapper());
    }

    @Test
    public void getAllCustomers_givenCustomerRepositoryIsNotEmpty_thenReturnListOfCustomerDTO(){
        Customer customer1=new Customer("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1");
        Customer customer2=new Customer("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2");
        customerService=new CustomerService(new CustomerRepository(newArrayList(customer1,customer2)),new CustomerMapper());

        Assertions.assertThat(customerService.getAllCustomers()).containsExactlyInAnyOrder(
                new CustomerDTO(customer1.getId(),customer1.getFirstName(),customer1.getLastName(),customer1.getAddress(),customer1.getEmailAddress(),customer1.getPhoneNumber()),
                new CustomerDTO(customer2.getId(),customer2.getFirstName(),customer2.getLastName(),customer2.getAddress(),customer2.getEmailAddress(),customer2.getPhoneNumber()));
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
        Assertions.assertThat(customerService.getAllCustomers().getLast()).isEqualTo(customerDTO);
    }

}
