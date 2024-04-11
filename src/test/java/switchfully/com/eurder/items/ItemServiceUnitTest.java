package switchfully.com.eurder.items;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.exceptions.ItemNotFoundException;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;

import java.util.Optional;
import java.util.UUID;

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
    @Test
    public void getOneItemById_givenIdExistInRepository_thenReturnItem(){
        Item item1 = new Item("nameTest","descriptionTest",10.0,5);

        Mockito.when(itemRepository.getOneItemById(item1.getId())).thenReturn(Optional.of(item1));

        Assertions.assertThat(itemService.getOneItemById(item1.getId())).isEqualTo(item1);
    }
    @Test
    public void getOneItemById_givenIdNotExistInRepository_thenThrowNewItemNotFoundException(){
        UUID fakeId=UUID.randomUUID();
        Mockito.when(itemRepository.getOneItemById(fakeId)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(()->itemService.getOneItemById(fakeId)).isInstanceOf(ItemNotFoundException.class);
    }
}