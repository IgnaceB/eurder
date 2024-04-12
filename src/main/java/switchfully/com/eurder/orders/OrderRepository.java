package switchfully.com.eurder.orders;

import org.springframework.stereotype.Repository;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public class OrderRepository {
    private List<Order> orders;

    public OrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    public OrderRepository() {
        this.orders=newArrayList();
    }

    protected Order createOrder(List<ItemGroup> listItemGroup, UUID userId, double totalPrice) {
        Order newOrder = new Order(listItemGroup,userId,totalPrice);
        this.orders.add(newOrder);
        return newOrder;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
