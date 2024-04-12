package switchfully.com.eurder.orders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.customers.CustomerMapper;
import switchfully.com.eurder.customers.CustomerRepository;
import switchfully.com.eurder.customers.CustomerService;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;
import switchfully.com.eurder.exceptions.CustomerNotFoundException;
import switchfully.com.eurder.exceptions.ItemNotFoundException;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.itemgroup.ItemGroupCreateDTO;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemMapper;
import switchfully.com.eurder.items.ItemRepository;
import switchfully.com.eurder.items.ItemService;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

public class OrderServiceIntegrationTest {

    private ItemService itemService = new ItemService(new ItemRepository(),new ItemMapper());

    private ItemGroupService itemGroupService = new ItemGroupService(itemService);
    private CustomerService customerService = new CustomerService(new CustomerRepository(),new CustomerMapper());
    private OrderRepository orderRepository=new OrderRepository();
    private OrderMapper orderMapper = new OrderMapper();
    private OrderService orderService;



    private CustomerDTO customerDTO;
    private ItemDTO itemDTO1;
    private ItemGroup itemGroup1;
    private ItemGroup itemGroup2;

    @BeforeEach
    void beforeEach(){
        CustomerCreateDTO customerCreateDTO = new CustomerCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789");
        customerDTO = customerService.createCustomer(customerCreateDTO);

        ItemCreateDTO itemCreateDTO1 = new ItemCreateDTO("nameTest", "descriptionTest", 10.0, 5);
        itemDTO1 = itemService.createItem(itemCreateDTO1);

        itemGroup1 = new ItemGroup(new Item(itemDTO1.getName(), itemDTO1.getDescription(), itemDTO1.getPrice(), 0), 3, LocalDate.now().plusDays(1));
        itemGroup2 = new ItemGroup(new Item(itemDTO1.getName(), itemDTO1.getDescription(), itemDTO1.getPrice(), 0), 7, LocalDate.now().plusDays(7));

        orderService = new OrderService(orderMapper,orderRepository,itemGroupService,customerService);
    }

    @Test
    void createOrder_givenOrderCreateDTOIsValid_thenReturnNewOrderDTO() {
        ItemGroupCreateDTO itemGroupCreateDTO1 = new ItemGroupCreateDTO(itemDTO1.getId(),itemGroup1.getAmount());
        ItemGroupCreateDTO itemGroupCreateDTO2 = new ItemGroupCreateDTO(itemDTO1.getId(),itemGroup2.getAmount());
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(newArrayList(itemGroupCreateDTO1,itemGroupCreateDTO2),customerDTO.getId());

        OrderDTO orderDTO = orderService.createOrder(orderCreateDTO);
        Assertions.assertThat(orderDTO.getUserId()).isEqualTo(customerDTO.getId());
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
    void CreateOrder_givenCustomerIdProvidedIsNotInCustomerRepository_thenThrowCustomerNotFoundException(){
        ItemGroupCreateDTO itemGroupCreateDTO1 = new ItemGroupCreateDTO(itemDTO1.getId(),itemGroup1.getAmount());
        ItemGroupCreateDTO itemGroupCreateDTO2 = new ItemGroupCreateDTO(itemDTO1.getId(),itemGroup2.getAmount());

        UUID fakeId=UUID.randomUUID();
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(newArrayList(itemGroupCreateDTO1,itemGroupCreateDTO2),fakeId);

        Assertions.assertThatThrownBy(()->orderService.createOrder(orderCreateDTO)).isInstanceOf(CustomerNotFoundException.class);

    }
    @Test
    void CreateOrder_givenItemIdProvidedIsNotInCustomerRepository_thenThrowItemNotFoundException(){
        UUID fakeId = UUID.randomUUID();
        ItemGroupCreateDTO itemGroupCreateDTO1 = new ItemGroupCreateDTO(itemDTO1.getId(),itemGroup1.getAmount());
        ItemGroupCreateDTO itemGroupCreateDTO2 = new ItemGroupCreateDTO(fakeId,itemGroup2.getAmount());
        OrderCreateDTO orderCreateDTO = new OrderCreateDTO(newArrayList(itemGroupCreateDTO1,itemGroupCreateDTO2),customerDTO.getId());

        Assertions.assertThatThrownBy(()->orderService.createOrder(orderCreateDTO)).isInstanceOf(ItemNotFoundException.class);

    }
}
