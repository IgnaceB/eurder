package switchfully.com.eurder.reports;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;
import switchfully.com.eurder.reports.DTO.ReportsDTO;
import switchfully.com.eurder.security.Feature;
import switchfully.com.eurder.security.SecurityService;

@RestController
@RequestMapping(path = "/reports")
public class ReportController {
    private SecurityService securityService;
    private ReportService reportService;

    public ReportController(SecurityService securityService, ReportService reportService) {
        this.securityService = securityService;
        this.reportService = reportService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ReportsDTO createOrder(@RequestHeader(value="Authorization") String auth){
        securityService.verifyAuthorization(auth, Feature.VIEW_ORDERS);
        return reportService.getReportsFromOneUser(securityService.getIdPassword(auth).getUserId());

    }
}
