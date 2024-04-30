package switchfully.com.eurder.itemgroup;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupCreateDTO;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupDTO;

@Component
public class ItemGroupMapper {

    public ItemGroupDTO toDTO(ItemGroup itemGroup){
        return new ItemGroupDTO(itemGroup.getItemName(),itemGroup.getAmountOrdered(),itemGroup.calculateTotalPriceOfTheGroup());
    }

}
