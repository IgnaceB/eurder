package switchfully.com.eurder.orders.dto;

import switchfully.com.eurder.itemgroup.ItemGroup;

import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private UUID id;
    private List<ItemGroup> listItemGroup;
    private UUID userId;

    private double totalPrice;

    public OrderDTO(UUID id, List<ItemGroup> listItemGroup, UUID userId, double totalPrice) {
        this.id = id;
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
