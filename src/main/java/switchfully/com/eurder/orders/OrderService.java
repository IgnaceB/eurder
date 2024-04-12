package switchfully.com.eurder.orders;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.users.UserService;
import switchfully.com.eurder.users.dto.UserDTO;
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
    private UserService userService;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemGroupService itemGroupService, UserService userService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemGroupService = itemGroupService;
        this.userService = userService;
    }

    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO) {
        List<ItemGroup> listItemGroup = createItemGroups(orderCreateDTO);

        UserDTO customerData = userService.getOneCustomerByID(orderCreateDTO.getUserId());
        Order order = orderRepository.createOrder(listItemGroup,customerData.getId(),calculateTotalPrice(listItemGroup));
        return orderMapper.toDto(order);


    }

    protected List<ItemGroup> createItemGroups(OrderCreateDTO orderCreateDTO) {
        return orderCreateDTO.getListItemGroupCreateDTO()
                .stream()
                .map(itemCreateGroupDTO->itemGroupService.createItemGroup(itemCreateGroupDTO))
                .toList();
    }

    protected double calculateTotalPrice(List<ItemGroup> listItemGroup) {
        return listItemGroup.stream()
                .map(ItemGroup::calculateTotalPriceOfTheGroup)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
