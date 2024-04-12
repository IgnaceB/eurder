package switchfully.com.eurder.orders;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.customers.CustomerService;
import switchfully.com.eurder.customers.dto.CustomerDTO;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;

import java.util.List;

@Service
public class OrderService {

    private OrderMapper orderMapper;
    private OrderRepository orderRepository;
    private ItemGroupService itemGroupService;
    private CustomerService customerService;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemGroupService itemGroupService, CustomerService customerService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemGroupService = itemGroupService;
        this.customerService = customerService;
    }

    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO) {
        List<ItemGroup> listItemGroup = orderCreateDTO.getListItemGroupCreateDTO()
                .stream()
                .map(itemCreateGroupDTO->itemGroupService.createItemGroup(itemCreateGroupDTO))
                .toList();

        CustomerDTO customerData = customerService.getOneCustomerByID(orderCreateDTO.getUserId());
        Order order = orderRepository.createOrder(listItemGroup,customerData.getId(),calculateTotalPrice(listItemGroup));
        return orderMapper.toDto(order);


    }

    protected static double calculateTotalPrice(List<ItemGroup> listItemGroup) {
        return listItemGroup.stream()
                .map(ItemGroup::calculateTotalPriceOfTheGroup)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
