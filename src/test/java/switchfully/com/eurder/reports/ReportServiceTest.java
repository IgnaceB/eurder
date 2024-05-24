package switchfully.com.eurder.reports;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupCreateDTO;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.items.ItemService;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.orders.OrderService;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;
import switchfully.com.eurder.reports.DTO.ReportsDTO;
import switchfully.com.eurder.users.UserService;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.utils.Name;

import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ReportServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    ReportService reportService;
    private UUID user1Id;

    @BeforeEach
    void beforeEach(){
        user1Id = userService.createCustomer(
                new UserCreateDTO(
                        new Name("firstnameTest","lastNameTest"),"test avenue 01 - 1000 TEST","email@test.test","0123456789","mdp"));
        UUID item1Id = itemService.createItem(
                new ItemCreateDTO("firstitem","description",10.00,20));
        OrderDTO Order1DTO = orderService.createOrder(
                new OrderCreateDTO(newArrayList(
                        new ItemGroupCreateDTO(item1Id,3),
                        new ItemGroupCreateDTO(item1Id,2))),user1Id);
        OrderDTO order2DTO = orderService.createOrder(
                new OrderCreateDTO(newArrayList(
                        new ItemGroupCreateDTO(item1Id,10))),user1Id);
    }

    @Test
    void getReportsFromOneUser_givenUserIdExist_thenReturnCorrectReportsDTO(){
        ReportsDTO reportsDTO = reportService.getReportsFromOneUser(user1Id);
        Assertions.assertThat(reportsDTO).isInstanceOf(reportsDTO.getClass());
    }


}