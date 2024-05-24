package switchfully.com.eurder.items;

import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemUpdateDTO;
import switchfully.com.eurder.users.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ItemServiceEndToEndTest {
    public static final String HOST="http://localhost";
    public static final String PATH="/items";
    public static final UUID ID_ITEM_1 = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    @LocalServerPort
    private int port;

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    private ItemController itemController;
    private static final String ADMIN_ID ="33f10c8b-7795-4fbc-adc3-cdea73f4fd4e";
    private static final String ADMIN_MDP = "mdp";
    public static final UUID USER_1_ID = UUID.fromString("e159d9f0-9023-4e2c-8ec0-6df42e763cf8");
    private static final String USER_PASSWORD = "mdp";

    @BeforeEach
    void setUp(){
        RestAssured.baseURI=HOST;
    }
    @Test
    @DirtiesContext
    void createItem_givenItemDTOIsValid_thenReturnTheNewItemDTO(){
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO("nameTest","descriptionTest",10.00,5);

        UUID itemId = RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .auth()
                .preemptive()
                .basic(ADMIN_ID,ADMIN_MDP)
                .body(itemCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UUID.class);

        Assertions.assertThat(itemId).isInstanceOf(UUID.class);
/*        Assertions.assertThat(entityManager.createQuery("select i from Item i where id=:id",Item.class).setParameter("id", itemId).getSingleResult())
                .isEqualTo()*/

    }
    private static Map<String, Object> getExpectedMapForFullyInvalidCreateUserDTO() {
        Map<String, Object> mapExpected = new HashMap<>();
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("name", "Name must be provided");
        errorsMap.put("price", "price has to be a positive double");
        errorsMap.put("amount", "amount has to be a positive integer");
        mapExpected.put("errors", errorsMap);
        return mapExpected;
    }

    @Test
    @DirtiesContext
    void createItem_givenItemCreateDTOIsTotallyInvalid_thenReturnMapOfErrors(){
       ItemCreateDTO itemCreateDTO = new ItemCreateDTO("","",-10.00,-5);
       HashMap errorsMap = RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
               .auth()
               .preemptive()
               .basic(ADMIN_ID,ADMIN_MDP)
                .body(itemCreateDTO)
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
    void createItem_givenUserHasNotTheRights_thenReturnStatus400(){
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO("nameTest","descriptionTest",10.00,5);
        RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .auth()
                .preemptive()
                .basic(USER_1_ID.toString(), USER_PASSWORD)
                .body(itemCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());



    }

    @Test
    @DirtiesContext
    void updateItem_givenUpdateItemDTOCorrectAndUserHasRights_thenReturnStatus200(){
        ItemUpdateDTO itemUpdateDTO = new ItemUpdateDTO("newName","newDescription",25.00,50);
        RestAssured.given()
                .accept(JSON)
                .contentType(JSON)
                .auth()
                .preemptive()
                .basic(ADMIN_ID,ADMIN_MDP)
                .body(itemUpdateDTO)
                .when()
                .port(port)
                .put(PATH+'/'+ID_ITEM_1)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());


    }
    @Test
    @DirtiesContext
    void updateItem_givenUpdateItemDTOCorrectAndUserHasNoRights_thenReturnStatus200(){
        ItemUpdateDTO itemUpdateDTO = new ItemUpdateDTO("newName","newDescription",25.00,50);
        RestAssured.given()
                .accept(JSON)
                .contentType(JSON)
                .auth()
                .preemptive()
                .basic(USER_1_ID.toString(), USER_PASSWORD)
                .body(itemUpdateDTO)
                .when()
                .port(port)
                .put(PATH+'/'+ID_ITEM_1)
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());


    }

}
