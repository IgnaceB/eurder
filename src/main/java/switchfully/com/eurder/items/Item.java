package switchfully.com.eurder.items;

import lombok.Data;

import java.util.UUID;
@Data
public class Item {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int amount;

    public Item(String name, String description, double price, int amount) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
}
