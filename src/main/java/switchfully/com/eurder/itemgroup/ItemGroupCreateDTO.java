package switchfully.com.eurder.itemgroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemGroupCreateDTO {
    @NotNull(message = "ItemId must be provided")
    private UUID itemId;

    private int amountOrdered;

    public ItemGroupCreateDTO(UUID itemId, int amountOrdered) {
        this.itemId=itemId;
        this.amountOrdered=amountOrdered;
    }

    public UUID getItemId() {
        return itemId;
    }

    public int getAmountOrdered() {
        return amountOrdered;
    }
}
