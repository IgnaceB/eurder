package switchfully.com.eurder.itemgroup;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupCreateDTO;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupDTO;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemService;
import switchfully.com.eurder.orders.Order;
import switchfully.com.eurder.users.User;
import switchfully.com.eurder.users.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ItemGroupService {
    public static final int DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK = 1;
    public static final int DAYS_BEFORE_SHIPPING_ITEM_OUT_OF_STOCK = 7;

    private ItemGroupRepository itemGroupRepository;
    private ItemService itemService;
    private ItemGroupMapper itemGroupMapper;
    private UserService userService;


    public ItemGroupService(ItemGroupRepository itemGroupRepository, ItemService itemService, ItemGroupMapper itemGroupMapper, UserService userService) {
        this.itemGroupRepository = itemGroupRepository;
        this.itemService = itemService;
        this.itemGroupMapper = itemGroupMapper;
        this.userService = userService;
    }

    public ItemGroup createItemGroup(ItemGroupCreateDTO itemGroupCreateDTO, Order order) {
        Item item = itemService.getOneItemById(itemGroupCreateDTO.getItemId());
        LocalDate shippingDate = calculateShippingDate(item.getAmount(),itemGroupCreateDTO.getAmountOrdered());
        //Shoudl I Create an entry in the mapper ???
        ItemGroup itemGroupToSave = new ItemGroup(UUID.randomUUID(),item,item.getPrice(),itemGroupCreateDTO.getAmountOrdered(),shippingDate,order);
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

/*    public List<ItemGroup> getItemGroupsFromOneOrder(UUID orderId) {
        return itemGroupRepository.findByOrder_id(orderId);
    }*/

    public ItemGroupDTO itemGroupToDTO(ItemGroup itemGroup){
        return itemGroupMapper.toDTO(itemGroup);
    }

    public List<ItemGroup> getItemGroupsFromOneUser(UUID userId) {
        User user = userService.getOneCustomerById(userId);
        return itemGroupRepository.findByOrderUser(user);
    }
}
