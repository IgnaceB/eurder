package switchfully.com.eurder.itemgroup;

import lombok.Data;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemService;

import java.time.LocalDate;
import java.util.Objects;


public class ItemGroup {
    private Item item;
    private int amount;
    private LocalDate shippingDate;

    public ItemGroup(Item item, int amount, LocalDate shippingDate) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = shippingDate;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }




}
