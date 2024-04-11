package switchfully.com.eurder.itemgroup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.items.ItemService;

@ExtendWith(MockitoExtension.class)
class ItemGroupServiceUnitTest {
    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemGroup itemGroup;

    @Test
    public void createItemGroup_givenItemGroupCreateDTOValidAndItemExistAndIsAvailable_thenReturnItemGroup(){

    }


}