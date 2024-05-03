package switchfully.com.eurder.reports;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupDTO;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.orders.Order;
import switchfully.com.eurder.orders.OrderService;
import switchfully.com.eurder.orders.dto.OrderDTO;
import switchfully.com.eurder.reports.DTO.ReportDTO;
import switchfully.com.eurder.reports.DTO.ReportsDTO;
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.users.User;
import switchfully.com.eurder.utils.Name;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

@ExtendWith(MockitoExtension.class)
class ReportServiceUnitTest {

    @Mock
    ReportMapper reportMapper;
    @Mock
    OrderService orderService;
    @Mock
    ItemGroupService itemGroupService;

    @InjectMocks
    ReportService reportService;

    public static final User USER_1 = new User(UUID.fromString("e159d9f0-9023-4e2c-8ec0-6df42e763cf8"), new Name("firstNameCustomer1", "LastNameCustomer1"), "emailCustomer1", "AddressCustomer1", "phoneNumberCustomer1", "mdp", Role.CUSTOMER);
    private List<ItemGroup> itemGroupList;
    private List<ItemGroupDTO> itemGroupDTOList;
    private Order order1;
    private Order order2;
    private List<ReportDTO> reportDTOList;

    @BeforeEach
    void beforeEach(){
        UUID uuidItem1 = UUID.randomUUID();
        UUID uuidItemGroup1 = UUID.randomUUID();
        UUID uuidItemGroup2 = UUID.randomUUID();
        UUID uuidItemGroup3 = UUID.randomUUID();
        UUID uuidOrder1 = UUID.randomUUID();
        UUID uuidOrder2 = UUID.randomUUID();
        order1 = new Order(uuidOrder1,USER_1);
        order2 = new Order(uuidOrder2,USER_1);
        OrderDTO orderDTO1 = new OrderDTO(uuidOrder1,60.00);
        OrderDTO orderDTO2 = new OrderDTO(uuidOrder2,30.00);
        Item item1 = new Item(uuidItem1,"nameTest","descriptionTest",10.0,5);
        ItemGroup itemGroup1 = new ItemGroup(uuidItemGroup1,item1,item1.getPrice(),3, LocalDate.now().plusDays(1), order1);
        ItemGroup itemGroup2 = new ItemGroup(uuidItemGroup2,item1,item1.getPrice(),3, LocalDate.now().plusDays(1), order1);
        ItemGroup itemGroup3 = new ItemGroup(uuidItemGroup3,item1,item1.getPrice(),3, LocalDate.now().plusDays(1), order2);
        ItemGroupDTO itemGroupDTO1 = new ItemGroupDTO(itemGroup1.getItemName(),itemGroup1.getAmountOrdered(),itemGroup1.calculateTotalPriceOfTheGroup());
        ItemGroupDTO itemGroupDTO2 = new ItemGroupDTO(itemGroup2.getItemName(),itemGroup2.getAmountOrdered(),itemGroup2.calculateTotalPriceOfTheGroup());
        ItemGroupDTO itemGroupDTO3 = new ItemGroupDTO(itemGroup3.getItemName(),itemGroup3.getAmountOrdered(),itemGroup3.calculateTotalPriceOfTheGroup());
        ReportDTO reportDTO1 = new ReportDTO(orderDTO1,newArrayList(itemGroupDTO1,itemGroupDTO2));
        ReportDTO reportDTO2 = new ReportDTO(orderDTO2, newArrayList(itemGroupDTO3));
        itemGroupDTOList = newArrayList(itemGroupDTO1,itemGroupDTO2,itemGroupDTO3);
        itemGroupList = newArrayList(itemGroup1,itemGroup2,itemGroup3);
        reportDTOList = newArrayList(reportDTO1, reportDTO2);
    }
    @Test
    void getReportsFromOneUser_givenCorrectUUID_thenReturnReportsDTOForThatUser(){
        Mockito.when(itemGroupService.getItemGroupsFromOneUser(USER_1.getId())).thenReturn(itemGroupList);

        Mockito.when(itemGroupService.itemGroupToDTO(itemGroupList.get(0))).thenReturn(itemGroupDTOList.get(0));
        Mockito.when(itemGroupService.itemGroupToDTO(itemGroupList.get(1))).thenReturn(itemGroupDTOList.get(1));
        Mockito.when(itemGroupService.itemGroupToDTO(itemGroupList.get(2))).thenReturn(itemGroupDTOList.get(2));

        Mockito.when(orderService.orderToDTO(order1,newArrayList(itemGroupList.get(0),itemGroupList.get(1)))).thenReturn(reportDTOList.getFirst().getOrderDTO());
        Mockito.when(orderService.orderToDTO(order2,newArrayList(itemGroupList.get(2)))).thenReturn(reportDTOList.getLast().getOrderDTO());

        Mockito.when(reportMapper.toDTO(reportDTOList.getFirst().getOrderDTO(),reportDTOList.getFirst().getItemGroupDTOS())).thenReturn(reportDTOList.getFirst());
        Mockito.when(reportMapper.toDTO(reportDTOList.getLast().getOrderDTO(),reportDTOList.getLast().getItemGroupDTOS())).thenReturn(reportDTOList.getLast());

        ReportsDTO reports = reportService.getReportsFromOneUser(USER_1.getId());
        Assertions.assertThat(reports.getListOfReports())
                .containsExactlyInAnyOrder(reportDTOList.getFirst(),reportDTOList.getLast());
        Assertions.assertThat(reports.getTotalPriceOfReports()).isEqualTo(90.00);
    }

}