package switchfully.com.eurder.reports;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupCreateDTO;
import switchfully.com.eurder.reports.DTO.ReportDTO;
import switchfully.com.eurder.reports.DTO.ReportsDTO;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ReportControllerEndToEndTest {

    public static final String HOST = "http://localhost";
    public static final String PATH = "/reports";
    public static final String USER_PASSWORD = "mdp";
    @LocalServerPort
    private int port;


    public static final UUID USER_1_ID = UUID.fromString("e159d9f0-9023-4e2c-8ec0-6df42e763cf8");
    public static final UUID ITEM_A_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    public static final UUID ITEM_B_ID = UUID.fromString("6ba7b810-9dad-11d1-80b4-00c04fd430c8");


    private List<ItemGroupCreateDTO> listItemGroupCreateDTO;
    private static final String ADMIN_ID = "33f10c8b-7795-4fbc-adc3-cdea73f4fd4e";
    private static final String ADMIN_MDP = "mdp";

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = HOST;
    }

    @Test
    @DirtiesContext
    public void viewReport_givenCustomerIsConnectedAndAllowed_thenReturnNewReportDTO(){
        ReportsDTO reportsDTO = RestAssured.given()
                .accept(JSON)
                .contentType(JSON)
                .auth()
                .preemptive()
                .basic(USER_1_ID.toString(), USER_PASSWORD)
                .when()
                .port(port)
                .get(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ReportsDTO.class);

        Assertions.assertThat(reportsDTO.getListOfReports()).hasSize(2);
    }
}
