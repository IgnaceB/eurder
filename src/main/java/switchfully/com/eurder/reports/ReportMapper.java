package switchfully.com.eurder.reports;

import org.springframework.stereotype.Component;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;
import switchfully.com.eurder.reports.DTO.ReportDTO;

import java.util.List;

@Component
public class ReportMapper {
    public ReportDTO toDTO(OrderDTO orderDTO, List<ItemGroupDTO> itemGroupDTOS){
        return new ReportDTO(orderDTO,itemGroupDTOS);
    }
}
