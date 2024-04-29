/*
package switchfully.com.eurder.itemgroup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemService;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class ItemGroupServiceUnitTest {
    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemGroupService itemGroupService;

*/
/*    @Test
    public void createItemGroup_givenItemGroupCreateDTOValidAndItemExistAndIsAvailable_thenReturnItemGroupWithNextDayShipping(){
        Item item1 = new Item("nameTest","descriptionTest",10.0,5);
        ItemGroup itemGroup = new ItemGroup(new Item(item1.getName(),item1.getDescription(),item1.getPrice(),0),3, LocalDate.now().plusDays(1));
        ItemGroupCreateDTO itemGroupCreateDTO = new ItemGroupCreateDTO(item1.getId(),item1.getAmount());
        Mockito.when(itemService.getOneItemById(item1.getId())).thenReturn(item1);

        ItemGroup itemGroupCreated = itemGroupService.createItemGroup(itemGroupCreateDTO);
        Assertions.assertThat(itemGroupCreated.getItem().getName()).isEqualTo(itemGroup.getItem().getName());
        Assertions.assertThat(itemGroupCreated.getItem().getDescription()).isEqualTo(itemGroup.getItem().getDescription());
        Assertions.assertThat(itemGroupCreated.getItem().getPrice()).isEqualTo(itemGroup.getItem().getPrice());
        Assertions.assertThat(itemGroupCreated.getItem().getAmount()).isEqualTo(itemGroup.getItem().getAmount());
        Assertions.assertThat(itemGroupCreated.getItem()).doesNotHaveSameHashCodeAs(itemGroup.getItem());

    }

    @Test
    public void createItemGroup_givenItemGroupCreateDTOValidAndItemExistAndIsNotAvailable_thenReturnItemGroupWith7DayShipping(){
        Item item1 = new Item("nameTest","descriptionTest",10.0,5);
        ItemGroup itemGroup = new ItemGroup(new Item(item1.getName(),item1.getDescription(),item1.getPrice(),0),3, LocalDate.now().plusDays(1));
        ItemGroupCreateDTO itemGroupCreateDTO = new ItemGroupCreateDTO(item1.getId(),item1.getAmount());
        Mockito.when(itemService.getOneItemById(item1.getId())).thenReturn(item1);

        ItemGroup itemGroupCreated = itemGroupService.createItemGroup(itemGroupCreateDTO);
        Assertions.assertThat(itemGroupCreated.getItem().getName()).isEqualTo(itemGroup.getItem().getName());
        Assertions.assertThat(itemGroupCreated.getItem().getDescription()).isEqualTo(itemGroup.getItem().getDescription());
        Assertions.assertThat(itemGroupCreated.getItem().getPrice()).isEqualTo(itemGroup.getItem().getPrice());
        Assertions.assertThat(itemGroupCreated.getItem().getAmount()).isEqualTo(itemGroup.getItem().getAmount());
        Assertions.assertThat(itemGroupCreated.getItem()).doesNotHaveSameHashCodeAs(itemGroup.getItem());

    }*//*



}*/
