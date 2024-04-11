package switchfully.com.eurder.items;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;

@ExtendWith(MockitoExtension.class)
class ItemServiceUnitTest {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemMapper itemMapper;
    @InjectMocks
    private ItemService itemService;

    @Test
    public void createItem_givenCreateItemDTOIsCorrect_thenReturnNewItemDTO(){
        ItemCreateDTO itemCreateDTO = new ItemCreateDTO("nameTest","descriptionTest",10.0,5);
        Item item = new Item(itemCreateDTO.getName(),itemCreateDTO.getDescription(),itemCreateDTO.getPrice(),itemCreateDTO.getAmount());
        ItemDTO itemDTO = new ItemDTO(item.getId(),item.getName(),item.getDescription(),item.getPrice(),item.getAmount());

        Mockito.when(itemRepository.createItem(itemCreateDTO)).thenReturn(item);
        Mockito.when(itemMapper.toDto(item)).thenReturn(itemDTO);

        Assertions.assertThat(itemService.createItem(itemCreateDTO)).isEqualTo(itemDTO);
    }
}