package switchfully.com.eurder.items;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import switchfully.com.eurder.security.Feature;
import switchfully.com.eurder.security.SecurityService;
import switchfully.com.eurder.users.UserController;
import switchfully.com.eurder.items.dto.ItemCreateDTO;
import switchfully.com.eurder.items.dto.ItemDTO;

@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ItemService itemService;
    @Autowired
    private SecurityService securityService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestHeader( value = "Authorization") String auth,@Valid @RequestBody ItemCreateDTO itemCreateDTO){
        securityService.verifyAuthorization(auth, Feature.VIEW_ONE_CUSTOMER);
        return itemService.createItem(itemCreateDTO);
    }

}
