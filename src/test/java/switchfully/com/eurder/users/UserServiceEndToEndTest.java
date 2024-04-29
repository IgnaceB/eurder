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
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;
import switchfully.com.eurder.utils.Name;

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
    public static final User USER_2 = new User(UUID.fromString("ec7658c0-36c1-4a62-b655-55226013228e"), new Name("firstNameCustomer2", "LastNameCustomer2"), "emailCustomer2", "AddressCustomer2", "phoneNumberCustomer2", "mdp", Role.CUSTOMER);
    public static final User USER_1 = new User(UUID.fromString("e159d9f0-9023-4e2c-8ec0-6df42e763cf8"), new Name("firstNameCustomer1", "LastNameCustomer1"), "emailCustomer1", "AddressCustomer1", "phoneNumberCustomer1", "mdp", Role.CUSTOMER);
    @LocalServerPort
    private int port;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    private UserController userController;
    private static final String ADMIN_ID= "33f10c8b-7795-4fbc-adc3-cdea73f4fd4e";
    private static final String ADMIN_MDP = "mdp";

    @BeforeEach
    void setUp(){
        RestAssured.baseURI=HOST;
    }
    @Test
    @DirtiesContext
    void createCustomer_givenCustomerCreateDTOIsValid_thenReturnTheNewCustomerDTO() {
        UserCreateDTO userCreateDTO = new UserCreateDTO(new Name("firstnameTest","lastNameTest"),"test avenue 01 - 1000 TEST","email@test.test","0123456789","mdp");

        UUID uuid = RestAssured.given()

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
                .as(UUID.class);

       Assertions.assertThat(uuid).isInstanceOf(UUID.class);


    }
    private static Map<String, Object> getExpectedMapForFullyInvalidCreateUserDTO() {
        Map<String, Object> mapExpected = new HashMap<>();
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("name.firstName", "must be provided");
        errorsMap.put("password", "password must be provided");
        errorsMap.put("name.lastName", "must be provided");
        errorsMap.put("address", "address must have at least 5 letters, maximum 100 letters");
        errorsMap.put("emailAddress", "Please provide a correct email address");
        errorsMap.put("phoneNumber", "Phone number must be between 7 and 15 numbers");
        mapExpected.put("errors", errorsMap);
        return mapExpected;
    }
    @Test
    void createCustomer_givenCustomerCreateDTOIsTotallyInvalid_thenReturnMapOfErrors() {
        UserCreateDTO userCreateDTO = new UserCreateDTO(new Name("",""),"te","emailtesttest","01","");

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

        UserDTO[] listOfCustomersDTO = RestAssured.given()
                .accept(JSON)
                .when()
                .port(port)
                .auth()
                .preemptive()
                .basic(ADMIN_ID,ADMIN_MDP)
                .get(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(UserDTO[].class);

        Assertions.assertThat(listOfCustomersDTO).containsExactlyInAnyOrder(
                new UserDTO(USER_1.getId(), USER_1.getFirstName(), USER_1.getLastName(), USER_1.getAddress(), USER_1.getEmailAddress(), USER_1.getPhoneNumber()),
                new UserDTO(USER_2.getId(), USER_2.getFirstName(), USER_2.getLastName(), USER_2.getAddress(), USER_2.getEmailAddress(), USER_2.getPhoneNumber()));

    }
    @Test
    @DirtiesContext
    void getAllCustomer_givenUnauthorizedUser_thenReturnStatus400(){


     RestAssured.given()
                .accept(JSON)
                .when()
                .port(port)
                .auth()
                .preemptive()
                .basic(USER_1.getId().toString(),USER_1.getPassword())
                .get(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());




    }
    @Test
    @DirtiesContext
    void getOneCustomerById_givenCustomerIdExistInRepository_thenReturnCustomerDTO(){

        UserDTO userDTO = RestAssured
                .given()
                .accept(JSON)
                .auth()
                .preemptive()
                .basic(ADMIN_ID,ADMIN_MDP)
                .when()
                .port(port)
                .get(PATH+"/"+ USER_1.getId().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(UserDTO.class);
        Assertions.assertThat(userDTO).isEqualTo(userMapper.toDTO(USER_1));

    }

    @Test
    void getOneCustomerById_givenCustomerIdDoesNotExistInRepository_thenReturnStatus404NotFound(){
        UUID fakeId = UUID.randomUUID();
        RestAssured
                .given()
                .accept(JSON)
                .auth()
                .preemptive()
                .basic(ADMIN_ID,ADMIN_MDP)
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
                .auth()
                .preemptive()
                .basic(ADMIN_ID,ADMIN_MDP)
                .when()
                .port(port)
                .get(PATH+"/thisIsNotAnUUID")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }
    @Test
    @DirtiesContext
    void getOneCustomerById_givenUnauthorizedUser_thenReturnStatus400(){

        RestAssured.given()
                .accept(JSON)
                .when()
                .port(port)
                .auth()
                .preemptive()
                .basic(USER_1.getId().toString(),USER_1.getPassword())
                .get(PATH+"/"+ USER_1.getId().toString())
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());




    }
}
