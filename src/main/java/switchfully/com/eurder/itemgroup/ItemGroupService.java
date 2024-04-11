package switchfully.com.eurder.itemgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemService;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ItemGroupService {
    public static final int DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK = 1;
    public static final int DAYS_BEFORE_SHIPPING_ITEM_OUT_OF_STOCK = 7;

    @Autowired
    private ItemService itemService;

    public ItemGroupService(ItemService itemService) {
        this.itemService = itemService;
    }

    public ItemGroup createItemGroup(ItemGroupCreateDTO itemGroupCreateDTO) {
        Item item = itemService.getOneItemById(itemGroupCreateDTO.getItemId());
        Item itemCopy = new Item(item.getName(),item.getDescription(),item.getPrice(),0);

        return new ItemGroup(itemCopy, itemGroupCreateDTO.getAmountOrdered(), calculateShippingDate(item.getAmount(), itemGroupCreateDTO.getAmountOrdered()));

    }

    private LocalDate calculateShippingDate(int amount, int amountOrdered) {
        if (amount>amountOrdered){
            return LocalDate.now().plusDays(DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK);
        }
        else {
            return LocalDate.now().plusDays(DAYS_BEFORE_SHIPPING_ITEM_OUT_OF_STOCK);

        }
    }
}
