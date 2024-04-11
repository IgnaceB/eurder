package switchfully.com.eurder.items;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ItemServiceEndToEndTest {
    public static final String HOST="http://localhost";
    public static final String PATH="/items";
    @LocalServerPort
    private int port;

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    private ItemController itemController;

    @BeforeEach
    void setUp(){
        RestAssured.baseURI=HOST;
    }
    @Test
    @DirtiesContext
    void createItem_givenItemDTOIsValid_thenReurnTheNewItemDTO(){
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO("nameTest","descriptionTest",10.00,5);

        ItemDTO itemDTO = RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .body(itemCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ItemDTO.class);

        Assertions.assertThat(itemDTO.getName()).isEqualTo(itemCreateDTO.getName());
        Assertions.assertThat(itemDTO.getDescription()).isEqualTo(itemCreateDTO.getDescription());
        Assertions.assertThat(itemDTO.getPrice()).isEqualTo(itemCreateDTO.getPrice());
        Assertions.assertThat(itemDTO.getAmount()).isEqualTo(itemCreateDTO.getAmount());

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

}
