package switchfully.com.eurder.items;

import org.springframework.stereotype.Repository;
import switchfully.com.eurder.items.dto.ItemCreateDTO;

import java.util.List;
import java.util.Optional;
import java.util.SequencedCollection;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public class ItemRepository {

    List<Item> items;

    public ItemRepository() {
        this.items=newArrayList();
    }

    public ItemRepository(List<Item> items) {
        this.items = items;
    }

    public Item createItem(ItemCreateDTO itemCreateDTO) {
        Item newItem = new Item(itemCreateDTO.getName(),
                itemCreateDTO.getDescription(),
                itemCreateDTO.getPrice(),
                itemCreateDTO.getAmount());
        items.add(newItem);
        return newItem;
    }

    public List<Item> getAllItems() {
        return this.items;
    }

    public Optional<Item> getOneItemById(UUID itemId) {
       return this.items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }
}
