package switchfully.com.eurder.orders;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.items.Item;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;


class OrderRepositoryTest {
    private OrderRepository orderRepository=new OrderRepository();

    @Test
    void createOrder_givenListOfGroupItemAndUserIdAndTotalPrice_thenReturnNewOrderAndAddItInOrders() {
        Item item1 = new Item("nameTest", "descriptionTest", 10.0, 5);
        ItemGroup itemGroup1 = new ItemGroup(new Item(item1.getName(), item1.getDescription(), item1.getPrice(), 0), 3, LocalDate.now().plusDays(1));
        ItemGroup itemGroup2 = new ItemGroup(new Item(item1.getName(), item1.getDescription(), item1.getPrice(), 0), 7, LocalDate.now().plusDays(1));
        List<ItemGroup> itemGroupList = newArrayList(itemGroup1,itemGroup2);
        UUID fakeId = UUID.randomUUID();
        orderRepository.createOrder(itemGroupList, fakeId,10);
        Assertions.assertThat(orderRepository.getOrders().getLast().getListItemGroup()).isEqualTo(itemGroupList);
        Assertions.assertThat(orderRepository.getOrders().getLast().getUserId()).isEqualTo(fakeId);
        Assertions.assertThat(orderRepository.getOrders().getLast().getTotalPrice()).isEqualTo(10);

    }

}