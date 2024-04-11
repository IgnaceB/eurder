package switchfully.com.eurder.itemgroup;

import java.util.UUID;

public class ItemGroupCreateDTO {
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
