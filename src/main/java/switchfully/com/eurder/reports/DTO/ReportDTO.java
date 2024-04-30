package switchfully.com.eurder.reports.DTO;

import switchfully.com.eurder.itemgroup.DTO.ItemGroupDTO;
import switchfully.com.eurder.orders.Order;
import switchfully.com.eurder.orders.dto.OrderDTO;

import java.util.List;

public class ReportDTO {
    private OrderDTO orderDTO;
    private List<ItemGroupDTO> itemGroups;


    public ReportDTO(OrderDTO orderDTO, List<ItemGroupDTO> itemGroups) {
        this.orderDTO = orderDTO;
        this.itemGroups = itemGroups;

    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }



    public List<ItemGroupDTO> getItemGroups() {
        return itemGroups;
    }
}
