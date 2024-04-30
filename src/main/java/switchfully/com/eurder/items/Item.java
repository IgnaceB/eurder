package switchfully.com.eurder.items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name="items")
public class Item {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name="price")
    private double price;
    @Column(name = "amount")
    private int amount;



    public Item(UUID id, String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
    public Item() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

}
