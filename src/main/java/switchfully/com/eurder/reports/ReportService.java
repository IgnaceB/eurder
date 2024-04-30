package switchfully.com.eurder.reports;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.itemgroup.ItemGroup;
import switchfully.com.eurder.itemgroup.ItemGroupService;
import switchfully.com.eurder.orders.Order;
import switchfully.com.eurder.orders.OrderService;
import switchfully.com.eurder.reports.DTO.ReportDTO;
import switchfully.com.eurder.reports.DTO.ReportsDTO;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

@Service
public class ReportService {

    ReportMapper reportMapper;
    OrderService orderService;
    ItemGroupService itemGroupService;

    public ReportService(ReportMapper reportMapper, OrderService orderService, ItemGroupService itemGroupService) {
        this.reportMapper = reportMapper;
        this.orderService = orderService;
        this.itemGroupService = itemGroupService;
    }
    public ReportsDTO getReportsFromOneUser(UUID userId){
        List<ItemGroup> itemGroupList = itemGroupService.getItemGroupsFromOneUser(userId);
        Map<Order, List<ItemGroup>> orderListMap = itemGroupList.stream()
                .collect(Collectors.groupingBy(ItemGroup::getOrder));
        List<Report> reportList = newArrayList();

        orderListMap.forEach((key, value) -> reportList.add(new Report(key, value)));

        List<ReportDTO> listOfReportDTO = reportList.stream()
                .map(report -> reportMapper.toDTO(orderService.orderToDTO(report.getOrder(),report.getItemGroups()),
                        report.getItemGroups().stream()
                                .map(itemGroup -> itemGroupService.itemGroupToDTO(itemGroup))
                                .collect(Collectors.toList())))
                .toList();

        return new ReportsDTO(listOfReportDTO,listOfReportDTO.stream()
                .mapToDouble(reportDTO -> reportDTO.getOrderDTO().getTotalPrice())
                .sum());

    }
}
