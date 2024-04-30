package switchfully.com.eurder.orders.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupCreateDTO;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Data
public class OrderCreateDTO {
/*    @NotBlank(message = "ListItemGroupCreateDTO must be provided")*/
    @NotNull
    private List<@Valid ItemGroupCreateDTO> listItemGroupCreateDTO;


   public OrderCreateDTO() {
    }

    public OrderCreateDTO(List<@Valid ItemGroupCreateDTO> listItemGroupCreateDTO) {
        this.listItemGroupCreateDTO=newArrayList(listItemGroupCreateDTO);

    }

}
