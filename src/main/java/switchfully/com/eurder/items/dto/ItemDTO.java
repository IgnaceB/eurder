package switchfully.com.eurder.items.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemDTO {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int amount;

    public ItemDTO(UUID id, String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
}
