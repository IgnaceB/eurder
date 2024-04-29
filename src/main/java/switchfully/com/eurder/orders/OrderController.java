package switchfully.com.eurder.orders;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;
import switchfully.com.eurder.security.Feature;
import switchfully.com.eurder.security.SecurityService;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private SecurityService securityService;

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestHeader(value="Authorization") String auth,@Valid @RequestBody OrderCreateDTO orderCreateDTO){
        securityService.verifyAuthorization(auth, Feature.ORDER_ITEMS);
        return orderService.createOrder(orderCreateDTO, securityService.getIdPassword(auth).getUserId());

    }
}
