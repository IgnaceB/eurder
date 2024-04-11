package switchfully.com.eurder.items;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.exceptions.ItemNotFoundException;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;

import java.util.UUID;

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

    public Item getOneItemById(UUID itemId) {
        return itemRepository.getOneItemById(itemId).orElseThrow(ItemNotFoundException::new);
    }
}
