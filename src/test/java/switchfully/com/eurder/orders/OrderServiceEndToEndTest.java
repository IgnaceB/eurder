package switchfully.com.eurder.orders;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import switchfully.com.eurder.customers.CustomerMapper;
import switchfully.com.eurder.customers.CustomerRepository;
import switchfully.com.eurder.customers.CustomerService;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.itemgroup.ItemGroupCreateDTO;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.items.*;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OrderServiceEndToEndTest {

        public static final String HOST="http://localhost";
        public static final String PATH="/orders";
        @LocalServerPort
        private int port;

        @Autowired
        private ItemService itemService;
        @Autowired
        private CustomerService customerService;
        @Autowired
        private ItemGroupService itemGroupService;
        @Autowired
        private OrderService orderService;


        private OrderController orderController;

        private CustomerDTO customerDTO;
        private ItemDTO itemDTO1;
        private ItemGroup itemGroup1;
        private ItemGroup itemGroup2;
        private List<ItemGroupCreateDTO> listItemGroupCreateDTO;


        @BeforeEach
        void setUp(){
            RestAssured.baseURI=HOST;
            CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789");
            customerDTO = customerService.createCustomer(customerCreateDTO);

            ItemCreateDTO itemCreateDTO1 = new ItemCreateDTO("nameTest", "descriptionTest", 10.0, 5);
            itemDTO1 = itemService.createItem(itemCreateDTO1);

            itemGroup1 = new ItemGroup(new Item(itemDTO1.getName(), itemDTO1.getDescription(), itemDTO1.getPrice(), 0), 3, LocalDate.now().plusDays(1));
            itemGroup2 = new ItemGroup(new Item(itemDTO1.getName(), itemDTO1.getDescription(), itemDTO1.getPrice(), 0), 7, LocalDate.now().plusDays(7));

            ItemGroupCreateDTO itemGroupCreateDTO1 = new ItemGroupCreateDTO(itemDTO1.getId(),3);
            ItemGroupCreateDTO itemGroupCreateDTO2 = new ItemGroupCreateDTO(itemDTO1.getId(),7);
            listItemGroupCreateDTO = newArrayList(itemGroupCreateDTO1,itemGroupCreateDTO2);
        }
        @Test
        @DirtiesContext
        void createOrder_givenOrderDTOIsValidAndRegisterCustomerIdExist_thenReturnTheNewOrderDTO(){
            OrderCreateDTO orderCreateDTO = new OrderCreateDTO(listItemGroupCreateDTO,customerDTO.getId());

            OrderDTO orderDTO = RestAssured.given()

                    .accept(JSON)
                    .contentType(JSON)
                    .body(orderCreateDTO)
                    .when()
                    .port(port)
                    .post(PATH)
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.CREATED.value())
                    .extract()
                    .as(OrderDTO.class);


            Assertions.assertThat(orderDTO.getTotalPrice()).isEqualTo(100.00);
            Assertions.assertThat(orderDTO.getListItemGroup()).hasSize(2);
            Assertions.assertThat(orderDTO.getListItemGroup().getFirst().getAmount()).isEqualTo(itemGroup1.getAmount());
            Assertions.assertThat(orderDTO.getListItemGroup().getLast().getAmount()).isEqualTo(itemGroup2.getAmount());
            Assertions.assertThat(orderDTO.getListItemGroup().getFirst().getItem().getName()).isEqualTo(itemGroup1.getItem().getName());
            Assertions.assertThat(orderDTO.getListItemGroup().getLast().getItem().getName()).isEqualTo(itemGroup2.getItem().getName());
            Assertions.assertThat(orderDTO.getListItemGroup().getFirst().getShippingDate()).isEqualTo(itemGroup1.getShippingDate());
            Assertions.assertThat(orderDTO.getListItemGroup().getLast().getShippingDate()).isEqualTo(itemGroup2.getShippingDate());
        }
    @Test
    @DirtiesContext
    void createOrder_givenOrderDTOIsValidAndRegisterCustomerIdDoesNotExist_thenReturnStatus404(){
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(listItemGroupCreateDTO, UUID.randomUUID());

        RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .body(orderCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }
    @Test
    @DirtiesContext
    void createOrder_givenOrderDTOIsValidButIdItemDoesNotExistAndRegisterCustomerIdExist_thenReturnStatus404(){

        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(newArrayList(new ItemGroupCreateDTO(UUID.randomUUID(),3)),customerDTO.getId());
        RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .body(orderCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }
    @Test
    @DirtiesContext
    void createOrder_givenOrderDTOIsNotValidButIdItemDoesNotExistAndRegisterCustomerIdExist_thenReturnStatus406(){

        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(newArrayList(new ItemGroupCreateDTO(null,-1)),customerDTO.getId());
        RestAssured.given()

                .accept(JSON)
                .contentType(JSON)
                .body(orderCreateDTO)
                .when()
                .port(port)
                .post(PATH)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value());

    }

}
