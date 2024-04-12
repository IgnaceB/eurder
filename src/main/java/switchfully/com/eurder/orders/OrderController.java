package switchfully.com.eurder.orders;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import switchfully.com.eurder.orders.dto.OrderCreateDTO;
import switchfully.com.eurder.orders.dto.OrderDTO;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@Valid @RequestBody OrderCreateDTO orderCreateDTO){
        return orderService.createOrder(orderCreateDTO);
    }
}
