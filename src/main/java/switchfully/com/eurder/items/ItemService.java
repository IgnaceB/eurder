package switchfully.com.eurder.items;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import switchfully.com.eurder.exceptions.ItemNotFoundException;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;
import switchfully.com.eurder.items.dto.ItemUpdateDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    ItemRepository itemRepository;
    ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public UUID createItem(ItemCreateDTO itemCreateDTO) {
        return itemRepository.save(
                        itemMapper.toItem(itemCreateDTO)).getId();
    }

    public Item getOneItemById(UUID itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(()-> new ItemNotFoundException("item not found"));
    }

    public void updateItem(ItemUpdateDTO itemUpdateDTO, UUID itemId) {
        Item itemToUpdate = getOneItemById(itemId);

        itemToUpdate = new Item(
                itemId,
                itemUpdateDTO.getName(),
                itemUpdateDTO.getDescription(),
                itemUpdateDTO.getPrice(),
                itemToUpdate.getAmount()
        );
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> itemMapper.toDto(item))
                .collect(Collectors.toList());
    }
}
