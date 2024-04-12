package switchfully.com.eurder.itemgroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class ItemGroupCreateDTO {
    @NotNull(message = "ItemId must be provided")
    private UUID itemId;
    @Positive
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
