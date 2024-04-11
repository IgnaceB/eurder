package switchfully.com.eurder.items;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import switchfully.com.eurder.customers.CustomerController;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ItemService itemService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@Valid @RequestBody ItemCreateDTO itemCreateDTO){
        return itemService.createItem(itemCreateDTO);
    }

}
