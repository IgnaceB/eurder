package switchfully.com.eurder.itemgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemService;
import switchfully.com.eurder.orders.Order;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ItemGroupService {
    public static final int DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK = 1;
    public static final int DAYS_BEFORE_SHIPPING_ITEM_OUT_OF_STOCK = 7;

    private ItemGroupRepository itemGroupRepository;
    private ItemService itemService;

    public ItemGroupService(ItemGroupRepository itemGroupRepository, ItemService itemService) {
        this.itemGroupRepository = itemGroupRepository;
        this.itemService = itemService;
    }

    public ItemGroup createItemGroup(ItemGroupCreateDTO itemGroupCreateDTO, Order order) {
        Item item = itemService.getOneItemById(itemGroupCreateDTO.getItemId());
        ItemGroup itemGroupToSave = new ItemGroup(UUID.randomUUID(),item,item.getPrice(),itemGroupCreateDTO.getAmountOrdered(),calculateShippingDate(item.getAmount(), itemGroupCreateDTO.getAmountOrdered()),order);
        return  itemGroupRepository.save(itemGroupToSave);
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
