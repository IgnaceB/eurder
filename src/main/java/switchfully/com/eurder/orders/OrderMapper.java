package switchfully.com.eurder.orders;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.orders.dto.OrderDTO;
import switchfully.com.eurder.users.User;

import java.util.UUID;

@Component
public class OrderMapper {
    public OrderDTO toDTO(Order order, double totalPrice) {
       return new OrderDTO(order.getId(), totalPrice);
    }
    public Order createToOrder(User user){
        return new Order(UUID.randomUUID(),user);
    }
}
