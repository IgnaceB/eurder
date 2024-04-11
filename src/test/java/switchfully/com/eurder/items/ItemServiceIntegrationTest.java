package switchfully.com.eurder.items;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;

import static org.assertj.core.util.Lists.newArrayList;

public class ItemServiceIntegrationTest {
    private ItemRepository itemRepository ;
    private ItemMapper itemMapper=new ItemMapper();
    private ItemService itemService;
    private Item item1;
    private Item item2;
    @BeforeEach
    void beforeEach(){
        item1=new Item("nameItem1","descriptionItem1",10.00,5);
        item2=new Item("nameItem2","descriptionItem2",20.00,10);
        itemRepository=new ItemRepository(newArrayList(item1,item2));
        itemService = new ItemService(itemRepository,itemMapper);
    }
    @Test
    void createItem_GivenItemCreateDTOIsFull_thenReturnItemDTO() {
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO("nameTest","descriptionTest",10.00,5);

        ItemDTO newItemDTO = itemService.createItem(itemCreateDTO);

        Assertions.assertThat(newItemDTO.getName()).isEqualTo(itemCreateDTO.getName());
        Assertions.assertThat(newItemDTO.getDescription()).isEqualTo(itemCreateDTO.getDescription());
        Assertions.assertThat(newItemDTO.getPrice()).isEqualTo(itemCreateDTO.getPrice());
        Assertions.assertThat(newItemDTO.getAmount()).isEqualTo(itemCreateDTO.getAmount());

        Assertions.assertThat(newItemDTO).isEqualTo(itemMapper.toDto(itemRepository.getAllItems().getLast()));
    }

    @Test
    void getOneItemById_givenItemIDExist_thenReturnItem(){
        Assertions.assertThat(itemService.getOneItemById(item1.getId())).isEqualTo(item1);
    }
}
