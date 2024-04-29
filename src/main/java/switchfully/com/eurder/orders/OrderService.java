package switchfully.com.eurder.orders;

import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import switchfully.com.eurder.users.User;
import switchfully.com.eurder.users.UserService;
import switchfully.com.eurder.users.dto.UserDTO;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
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


    //SRP not ok -> how could I keep my SRP when the ItemGroups entries have to be created inside the order
    public OrderDTO createOrder(OrderCreateDTO orderCreateDTO, UUID userId) {
        User user = userService.getOneCustomerById(userId);

        Order order = orderMapper.createToOrder(user);
        orderRepository.save(order);

        List<ItemGroup> listItemGroup = createItemGroups(orderCreateDTO, order);


        return orderMapper.toDTO(order,calculateTotalPrice(listItemGroup));


    }

    private List<ItemGroup> createItemGroups(OrderCreateDTO orderCreateDTO, Order order) {
        List<ItemGroup> listItemGroup = orderCreateDTO.getListItemGroupCreateDTO().stream()
                .map(itemGroupCreateDTO -> itemGroupService.createItemGroup(itemGroupCreateDTO, order))
                .toList();
        return listItemGroup;
    }

    protected double calculateTotalPrice(List<ItemGroup> listItemGroup) {
        return listItemGroup.stream()
                .map(ItemGroup::calculateTotalPriceOfTheGroup)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
