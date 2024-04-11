package switchfully.com.eurder.items.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemCreateDTO {
    @NotBlank(message = "Name must be provided")
    private String name;
    private String description;
    @Positive(message = "price has to be a positive double")
    private double price;
    @Positive(message = "amount has to be a positive integer")
    private int amount;

    public ItemCreateDTO(String name, String description, double price, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }
}
