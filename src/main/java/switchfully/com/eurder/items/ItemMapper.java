package switchfully.com.eurder.items;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;
import switchfully.com.eurder.items.dto.ItemUpdateDTO;

import java.util.UUID;

@Component
public class ItemMapper {
    public ItemDTO toDto(Item item) {
        return new ItemDTO(item.getId(),item.getName(),item.getDescription(),item.getPrice(),item.getAmount());
    }

    public Item toItem(ItemCreateDTO itemCreateDTO) {
        return new Item(UUID.randomUUID(),itemCreateDTO.getName(),itemCreateDTO.getDescription(),itemCreateDTO.getPrice(),itemCreateDTO.getAmount());
    }


}
