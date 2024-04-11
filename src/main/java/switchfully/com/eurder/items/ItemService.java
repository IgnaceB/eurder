package switchfully.com.eurder.items;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;
@Service
public class ItemService {

    ItemRepository itemRepository;
    ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDTO createItem(ItemCreateDTO itemCreateDTO) {
        return itemMapper.toDto(
                itemRepository.createItem(itemCreateDTO));
    }
}
