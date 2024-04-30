/*
package switchfully.com.eurder.orders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.users.User;
import switchfully.com.eurder.users.UserService;
import switchfully.com.eurder.users.dto.UserDTO;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupCreateDTO;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {
    @Mock
    UserService userService;

    @Mock
    ItemGroupService itemGroupService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderMapper orderMapper;
    @InjectMocks
    OrderService orderService;

    private Item item1;
    private ItemGroup itemGroup1;
    private ItemGroup itemGroup2;

    @BeforeEach
    void beforeEach() {
        item1 = new Item("nameTest", "descriptionTest", 10.0, 5);
        itemGroup1 = new ItemGroup(new Item(item1.getName(), item1.getDescription(), item1.getPrice(), 0), 3, LocalDate.now().plusDays(1));
        itemGroup2 = new ItemGroup(new Item(item1.getName(), item1.getDescription(), item1.getPrice(), 0), 7, LocalDate.now().plusDays(1));

    }

    @Test
    void calculateTotalPrice_givenListItemGroupNotEmpty_thenReturnTotalPriceOfAllGroups() {
        List<ItemGroup> listItemGroup = newArrayList(itemGroup1,itemGroup2) ;
        Assertions.assertThat(orderService.calculateTotalPrice(listItemGroup)).isEqualTo(100.00);
    }

    @Test
    void createOrder_givenOrderCreateDTOValid_thenReturnNewOrderDTO() {
        User user1 = new User("firstNameCustomer1", "LastNameCustomer1", "emailCustomer1", "AddressCustomer1", "phoneNumberCustomer1","mdp");
        UserDTO userDTO1 = new UserDTO(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getAddress(), user1.getEmailAddress(), user1.getPhoneNumber());

        ItemGroupCreateDTO itemGroup1CreateDTO = new ItemGroupCreateDTO(item1.getId(), item1.getAmount());
        ItemGroupCreateDTO itemGroup2CreateDTO = new ItemGroupCreateDTO(item1.getId(), item1.getAmount());

        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(newArrayList(itemGroup1CreateDTO, itemGroup2CreateDTO));
        Order order = new Order(newArrayList(itemGroup1, itemGroup2), user1.getId(), 100.00);
        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getListItemGroup(), order.getUserId(), order.getTotalPrice());

        Mockito.when(userService.getOneCustomerByID(user1.getId())).thenReturn(userDTO1);
        Mockito.when(itemGroupService.createItemGroup(itemGroup1CreateDTO)).thenReturn(itemGroup1);
        Mockito.when(itemGroupService.createItemGroup(itemGroup2CreateDTO)).thenReturn(itemGroup2);
        Mockito.when(orderRepository.createOrder(orderService.createItemGroups(orderCreateDTO), userDTO1.getId(), orderService.calculateTotalPrice(orderService.createItemGroups(orderCreateDTO)))).thenReturn(order);
        Mockito.when(orderMapper.toDto(order)).thenReturn(orderDTO);

        OrderDTO producedOrderDTO = orderService.createOrder(orderCreateDTO,user1.getId());

        Assertions.assertThat(producedOrderDTO).isEqualTo(orderDTO);

    }
}*/
