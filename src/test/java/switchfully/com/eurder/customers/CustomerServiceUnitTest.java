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
import switchfully.com.eurder.exceptions.CustomerNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

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
        Mockito.when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        Assertions.assertThat(customerService.createCustomer(customerCreateDTO)).isEqualTo(customerDTO);
    }
    @Test
    public void getAllCustomers_givenCustomersInRepository_thenReturnListOfCustomerDTO(){
        Customer customer1=new Customer("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1");
        Customer customer2=new Customer("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2");
        CustomerDTO customerDTO1 = new CustomerDTO(customer1.getId(),customer1.getFirstName(),customer1.getLastName(),customer1.getAddress(),customer1.getEmailAddress(),customer1.getPhoneNumber());
        CustomerDTO customerDTO2 = new CustomerDTO(customer2.getId(),customer2.getFirstName(),customer2.getLastName(),customer2.getAddress(),customer2.getEmailAddress(),customer2.getPhoneNumber());

        Mockito.when(customerRepository.getAllCustomers()).thenReturn(newArrayList(customer1,customer2));
        Mockito.when(customerMapper.toDTO(customer1)).thenReturn(customerDTO1);
        Mockito.when(customerMapper.toDTO(customer2)).thenReturn(customerDTO2);

        Assertions.assertThat(customerService.getAllCustomers()).containsExactlyInAnyOrder(
                customerDTO1,customerDTO2);
    }
    @Test
    public void getAllCustomers_givenNoInRepository_thenReturnEmptyList(){
        Mockito.when(customerRepository.getAllCustomers()).thenReturn(newArrayList());
        Assertions.assertThat(customerService.getAllCustomers()).containsExactlyInAnyOrder();
    }
    @Test
    public void getOneCustomerById_givenIdExistInRepository_thenReturnCustomerDTO(){
        Customer customer1=new Customer("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1");
        CustomerDTO customerDTO1 = new CustomerDTO(customer1.getId(),customer1.getFirstName(),customer1.getLastName(),customer1.getAddress(),customer1.getEmailAddress(),customer1.getPhoneNumber());

        Mockito.when(customerRepository.getOneCustomerById(customer1.getId())).thenReturn(Optional.of(customer1));
        Mockito.when(customerMapper.toDTO(customer1)).thenReturn(customerDTO1);

        Assertions.assertThat(customerService.getOneCustomerByID(customer1.getId())).isEqualTo(customerDTO1);
    }
    @Test
    public void getOneCustomerById_givenIdDoesntExistInRepository_thenThrowCustomerNotFoundException(){
        UUID fakeId = UUID.randomUUID();
        Mockito.when(customerRepository.getOneCustomerById(fakeId)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(()-> customerService.getOneCustomerByID(fakeId)).isInstanceOf(CustomerNotFoundException.class);
    }
}