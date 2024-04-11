package switchfully.com.eurder.customers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.util.Lists.newArrayList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerServiceEndToEndTest {

    public static final String HOST="http://localhost";
    public static final String PATH="/customers";
    @LocalServerPort
    private int port;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    private CustomerController customerController;

    @BeforeEach
    void setUp(){
        RestAssured.baseURI=HOST;
    }
    @Test
    void createCustomer_givenCustomerCreateDTOIsValid_thenReturnTheNewCustomerDTO() {
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789");

        CustomerDTO customerDTO = RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .body(customerCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CustomerDTO.class);

        Assertions.assertThat(customerDTO.getFirstName()).isEqualTo(customerCreateDTO.getFirstName());
        Assertions.assertThat(customerDTO.getLastName()).isEqualTo(customerCreateDTO.getLastName());
        Assertions.assertThat(customerDTO.getAddress()).isEqualTo(customerCreateDTO.getAddress());
        Assertions.assertThat(customerDTO.getEmailAddress()).isEqualTo(customerCreateDTO.getEmailAddress());
        Assertions.assertThat(customerDTO.getPhoneNumber()).isEqualTo(customerCreateDTO.getPhoneNumber());


    }
    private static Map<String, Object> getExpectedMapForFullyInvalidCreateUserDTO() {
        Map<String, Object> mapExpected = new HashMap<>();
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("firstName", "First name must be provided");
        errorsMap.put("lastName", "Last name must be provided");
        errorsMap.put("address", "address must have at least 5 letters, maximum 100 letters");
        errorsMap.put("emailAddress", "Please provide a correct email address");
        errorsMap.put("phoneNumber", "Phone number must be between 7 and 15 numbers");
        mapExpected.put("errors", errorsMap);
        return mapExpected;
    }
    @Test
    void createCustomer_givenCustomerCreateDTOIsTotallyInvalid_thenReturnListOfErrors() {
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO("","","te","emailtesttest","01");

        HashMap errorsMap = RestAssured.given()
                .accept(JSON)
                .contentType(JSON)
                .body(customerCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .extract()
                .as(HashMap.class);

        Assertions.assertThat(errorsMap).containsAllEntriesOf(getExpectedMapForFullyInvalidCreateUserDTO());
    }

    @Test
    void getAllCustomer_givenCustomerRepositoryIsNotNull_thenReturnListOfCustomerDTO(){
        CustomerCreateDTO createCustomer1=new CustomerCreateDTO("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1");
        CustomerCreateDTO createCustomer2=new CustomerCreateDTO("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2");
        Customer customer1 = customerRepository.createCustomer(createCustomer1);
        Customer customer2 = customerRepository.createCustomer(createCustomer2);

        CustomerDTO[] listOfCustomersDTO = RestAssured.given()
                .accept(JSON)
                .when()
                .port(port)
                .get(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(CustomerDTO[].class);

        Assertions.assertThat(listOfCustomersDTO).containsExactlyInAnyOrder(
                new CustomerDTO(customer1.getId(),customer1.getFirstName(),customer1.getLastName(),customer1.getAddress(),customer1.getEmailAddress(),customer1.getPhoneNumber()),
                new CustomerDTO(customer2.getId(),customer2.getFirstName(),customer2.getLastName(),customer2.getAddress(),customer2.getEmailAddress(),customer2.getPhoneNumber()));

    }
}
