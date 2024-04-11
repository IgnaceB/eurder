package switchfully.com.eurder.items;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.items.dto.ItemDTO;
@Component
public class ItemMapper {
    public ItemDTO toDto(Item item) {
        return new ItemDTO(item.getId(),item.getName(),item.getDescription(),item.getPrice(),item.getAmount());
    }
}
