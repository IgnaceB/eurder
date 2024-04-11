package switchfully.com.eurder.customers;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

import static io.restassured.http.ContentType.JSON;
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
                .port(port)
                .accept(JSON)
                .contentType(JSON)
                .body(customerCreateDTO)
                .when()
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

}
