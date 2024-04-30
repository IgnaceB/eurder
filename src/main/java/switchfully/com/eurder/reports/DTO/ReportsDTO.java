package switchfully.com.eurder.reports.DTO;

import switchfully.com.eurder.itemgroup.DTO.ItemGroupDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;
import switchfully.com.eurder.reports.Report;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReportsDTO {
    private List<ReportDTO> listOfReports;
    private double totalPriceOfOrders;

    public ReportsDTO(List<ReportDTO> listOfReports, double totalPriceOfOrders) {
        this.listOfReports = listOfReports;
        this.totalPriceOfOrders = totalPriceOfOrders;
    }

    public List<ReportDTO> getListOfReports() {
        return listOfReports;
    }

    public double getTotalPriceOfOrders() {
        return totalPriceOfOrders;
    }
}
