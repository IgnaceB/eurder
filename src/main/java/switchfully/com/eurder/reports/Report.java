package switchfully.com.eurder.reports;

import lombok.Getter;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.orders.Order;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class Report {
    private Order order;
    private List<ItemGroup> itemGroups;


    public Report(Order order, List<ItemGroup> itemGroups) {
        this.order = order;
        this.itemGroups = itemGroups;

    }
}
