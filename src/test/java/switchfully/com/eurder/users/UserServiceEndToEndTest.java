package switchfully.com.eurder.users;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.util.Lists.newArrayList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserServiceEndToEndTest {

    public static final String HOST="http://localhost";
    public static final String PATH="/customers";
    @LocalServerPort
    private int port;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setUp(){
        RestAssured.baseURI=HOST;
    }
    @Test
    @DirtiesContext
    void createCustomer_givenCustomerCreateDTOIsValid_thenReturnTheNewCustomerDTO() {
        UserCreateDTO userCreateDTO = new UserCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789","mdp");

        UserDTO userDTO = RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .body(userCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDTO.class);

        Assertions.assertThat(userDTO.getFirstName()).isEqualTo(userCreateDTO.getFirstName());
        Assertions.assertThat(userDTO.getLastName()).isEqualTo(userCreateDTO.getLastName());
        Assertions.assertThat(userDTO.getAddress()).isEqualTo(userCreateDTO.getAddress());
        Assertions.assertThat(userDTO.getEmailAddress()).isEqualTo(userCreateDTO.getEmailAddress());
        Assertions.assertThat(userDTO.getPhoneNumber()).isEqualTo(userCreateDTO.getPhoneNumber());


    }
    private static Map<String, Object> getExpectedMapForFullyInvalidCreateUserDTO() {
        Map<String, Object> mapExpected = new HashMap<>();
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("firstName", "First name must be provided");
        errorsMap.put("password", "password must be provided");
        errorsMap.put("lastName", "Last name must be provided");
        errorsMap.put("address", "address must have at least 5 letters, maximum 100 letters");
        errorsMap.put("emailAddress", "Please provide a correct email address");
        errorsMap.put("phoneNumber", "Phone number must be between 7 and 15 numbers");
        mapExpected.put("errors", errorsMap);
        return mapExpected;
    }
    @Test
    void createCustomer_givenCustomerCreateDTOIsTotallyInvalid_thenReturnMapOfErrors() {
        UserCreateDTO userCreateDTO = new UserCreateDTO("","","te","emailtesttest","01","");

        HashMap errorsMap = RestAssured.given()
                .accept(JSON)
                .contentType(JSON)
                .body(userCreateDTO)
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
    @DirtiesContext
    void getAllCustomer_givenCustomerRepositoryIsNotNull_thenReturnListOfCustomerDTO(){
        UserCreateDTO createCustomer1=new UserCreateDTO("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1","mdp");
        UserCreateDTO createCustomer2=new UserCreateDTO("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2","mdp");
        User user1 = userRepository.createCustomer(createCustomer1);
        User user2 = userRepository.createCustomer(createCustomer2);

        UserDTO[] listOfCustomersDTO = RestAssured.given()
                .accept(JSON)
                .when()
                .port(port)
                .get(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(UserDTO[].class);

        Assertions.assertThat(listOfCustomersDTO).containsExactlyInAnyOrder(
                new UserDTO(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getAddress(), user1.getEmailAddress(), user1.getPhoneNumber()),
                new UserDTO(user2.getId(), user2.getFirstName(), user2.getLastName(), user2.getAddress(), user2.getEmailAddress(), user2.getPhoneNumber()));

    }
    @Test
    @DirtiesContext
    void getOneCustomerById_givenCustomerIdExistInRepository_thenReturnCustomerDTO(){
        UserCreateDTO createCustomer1=new UserCreateDTO("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1","mdp");
        UserCreateDTO createCustomer2=new UserCreateDTO("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2","mdp");
        User user1 = userRepository.createCustomer(createCustomer1);
        User user2 = userRepository.createCustomer(createCustomer2);

        UserDTO userDTO = RestAssured
                .given()
                .accept(JSON)
                .when()
                .port(port)
                .get(PATH+"/"+ user1.getId().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(UserDTO.class);
        Assertions.assertThat(userDTO).isEqualTo(userMapper.toDTO(user1));

    }

    @Test
    void getOneCustomerById_givenCustomerIdDoesNotExistInRepository_thenReturnStatus404NotFound(){
        UUID fakeId = UUID.randomUUID();
        RestAssured
                .given()
                .accept(JSON)
                .when()
                .port(port)
                .get(PATH+"/"+fakeId.toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }
    @Test
    void getOneCustomerById_givenCustomerIdIncorrectFormat_thenReturnStatus400BadRequest(){
        RestAssured
                .given()
                .accept(JSON)
                .when()
                .port(port)
                .get(PATH+"/thisIsNotAnUUID")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }
}
