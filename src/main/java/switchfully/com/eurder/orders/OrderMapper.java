package switchfully.com.eurder.orders;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.orders.dto.OrderDTO;
@Component
public class OrderMapper {
    public OrderDTO toDto(Order order) {
       return new OrderDTO(order.getId(),order.getListItemGroup(),order.getUserId(), order.getTotalPrice());
    }
}
