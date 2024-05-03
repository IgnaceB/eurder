package switchfully.com.eurder.reports.DTO;

import java.util.List;

public class ReportsDTO {
    private List<ReportDTO> listOfReports;
    private double totalPriceOfReports;

    public ReportsDTO(List<ReportDTO> listOfReports, double totalPriceOfOrders) {
        this.listOfReports = listOfReports;
        this.totalPriceOfReports = totalPriceOfOrders;
    }

    public List<ReportDTO> getListOfReports() {
        return listOfReports;
    }

    public double getTotalPriceOfReports() {
        return totalPriceOfReports;
    }
}
