package switchfully.com.eurder.customers;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.util.Lists.newArrayList;


class CustomerRepositoryTest {

    private CustomerRepository customerRepository;
    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void beforeEach(){
        customer1=new Customer("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1");
        customer2=new Customer("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2");
        customerRepository=new CustomerRepository(newArrayList(customer1,customer2));
    }

    @Test
    void getOneCustomerById_givenUUIDExistInRepository_returnOptionalCustomerWithThisID() {
        Assertions.assertThat(customerRepository.getOneCustomerById(customer1.getId())).isEqualTo(Optional.of(customer1));
    }
}