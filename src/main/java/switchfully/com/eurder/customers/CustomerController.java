package switchfully.com.eurder.customers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import switchfully.com.eurder.customers.dto.CustomerCreateDTO;
import switchfully.com.eurder.customers.dto.CustomerDTO;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping (produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerCreateDTO customerCreateDTO){
        return customerService.createCustomer(customerCreateDTO);
    }

}
