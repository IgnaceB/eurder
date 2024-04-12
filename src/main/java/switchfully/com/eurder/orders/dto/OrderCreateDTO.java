package switchfully.com.eurder.orders.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import switchfully.com.eurder.itemgroup.ItemGroupCreateDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

@Data
public class OrderCreateDTO {
/*    @NotBlank(message = "ListItemGroupCreateDTO must be provided")*/
    @NotNull
    private List<@Valid ItemGroupCreateDTO> listItemGroupCreateDTO;
    @NotNull
    private UUID userId;



    public OrderCreateDTO() {
        this.listItemGroupCreateDTO=newArrayList();
    }

    public OrderCreateDTO(List<@Valid ItemGroupCreateDTO> listItemGroupCreateDTO,UUID userId) {
        this.listItemGroupCreateDTO=newArrayList(listItemGroupCreateDTO);
        this.userId=userId;

    }
}
