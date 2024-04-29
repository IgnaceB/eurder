package switchfully.com.eurder.orders.dto;

import switchfully.com.eurder.itemgroup.ItemGroup;

import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private UUID id;

    private double totalPrice;

    public OrderDTO(UUID id, double totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
