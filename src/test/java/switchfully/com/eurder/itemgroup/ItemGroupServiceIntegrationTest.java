package switchfully.com.eurder.itemgroup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemMapper;
import switchfully.com.eurder.items.ItemRepository;
import switchfully.com.eurder.items.ItemService;

import java.time.LocalDate;

import static org.assertj.core.util.Lists.newArrayList;

public class ItemGroupServiceIntegrationTest {

    private ItemRepository itemRepository ;
    private ItemMapper itemMapper=new ItemMapper();
    private ItemService itemService;
    private ItemGroupService itemGroupService;
    private Item item1;
    private Item item2;
    @BeforeEach
    void beforeEach(){
        item1=new Item("nameItem1","descriptionItem1",10.00,5);
        item2=new Item("nameItem2","descriptionItem2",20.00,10);
        itemRepository=new ItemRepository(newArrayList(item1,item2));
        itemService = new ItemService(itemRepository,itemMapper);
        itemGroupService = new ItemGroupService(itemService);
    }

    @Test
    void createItemGroup_givenIdExist_thenReturnItemGroupWithCalculatedShippingDate(){
        ItemGroup itemGroup = new ItemGroup(item1,7, LocalDate.now().plusDays(7));
        ItemGroupCreateDTO itemGroupCreateDTO = new ItemGroupCreateDTO(item1.getId(),7);

        ItemGroup itemGroupCreated = itemGroupService.createItemGroup(itemGroupCreateDTO);

        Assertions.assertThat(itemGroupCreated.getItem().getName()).isEqualTo(itemGroup.getItem().getName());
        Assertions.assertThat(itemGroupCreated.getItem().getDescription()).isEqualTo(itemGroup.getItem().getDescription());
        Assertions.assertThat(itemGroupCreated.getItem().getPrice()).isEqualTo(itemGroup.getItem().getPrice());
        Assertions.assertThat(itemGroupCreated.getItem().getAmount()).isEqualTo(0);
        Assertions.assertThat(itemGroupCreated.getItem()).doesNotHaveSameHashCodeAs(itemGroup.getItem());
    }
}
