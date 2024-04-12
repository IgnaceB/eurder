package switchfully.com.eurder.orders;

import switchfully.com.eurder.itemgroup.ItemGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;
    private List<ItemGroup> listItemGroup;
    private UUID userId;

    private double totalPrice;

    public Order(List<ItemGroup> listItemGroup, UUID userId, double totalPrice) {
        this.id=UUID.randomUUID();
        this.listItemGroup = listItemGroup;
        this.userId = userId;
        this.totalPrice = totalPrice;

    }

    public UUID getId() {
        return id;
    }

    public List<ItemGroup> getListItemGroup() {
        return listItemGroup;
    }

    public UUID getUserId() {
        return userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
