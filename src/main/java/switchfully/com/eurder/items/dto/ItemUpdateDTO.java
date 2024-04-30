package switchfully.com.eurder.items.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.UUID;
@Getter
public class ItemUpdateDTO {
    @NotBlank
    private final String name;
    @NotNull
    private final String description;
    @Positive
    private final double price;
    @NotNull
    private final int amount;


    public ItemUpdateDTO(String name,String description, double price, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

}
