package switchfully.com.eurder.items;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.customers.Customer;
import switchfully.com.eurder.customers.CustomerRepository;

import java.util.Optional;

import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository;
    private Item item1;
    private Item item2;

    @BeforeEach
    void beforeEach(){
        item1=new Item("nameItem1","descriptionItem1",10.00,5);
        item2=new Item("nameItem2","descriptionItem2",20.00,10);
        itemRepository=new ItemRepository(newArrayList(item1,item2));
    }

    @Test
    public void getOneItemById_givenId_returnOptionalItem(){
        Assertions.assertThat(itemRepository.getOneItemById(item1.getId())).isEqualTo(Optional.of(item1));
    }

}