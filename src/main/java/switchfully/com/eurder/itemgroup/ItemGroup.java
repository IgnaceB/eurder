package switchfully.com.eurder.itemgroup;

import lombok.Data;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemService;

import java.time.LocalDate;
@Data
public class ItemGroup {
    private Item item;
    private int amount;
    private LocalDate shippingDate;




}
