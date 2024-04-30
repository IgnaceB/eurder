package switchfully.com.eurder.itemgroup;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.itemgroup.DTO.ItemGroupCreateDTO;
import switchfully.com.eurder.items.Item;
import switchfully.com.eurder.items.ItemService;
import switchfully.com.eurder.orders.Order;
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.users.User;
import switchfully.com.eurder.utils.Name;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ItemGroupServiceUnitTest {
    @Mock
    private ItemService itemService;
    @Mock
    private ItemGroupRepository itemGroupRepository;

    @InjectMocks
    private ItemGroupService itemGroupService;

    public static final User USER_1 = new User(UUID.fromString("e159d9f0-9023-4e2c-8ec0-6df42e763cf8"), new Name("firstNameCustomer1", "LastNameCustomer1"), "emailCustomer1", "AddressCustomer1", "phoneNumberCustomer1", "mdp", Role.CUSTOMER);
    @Test
    public void createItemGroup_givenItemGroupCreateDTOValidAndItemExistAndIsAvailable_thenReturnItemGroupWithNextDayShipping(){
        UUID uuidItem = UUID.randomUUID();

        Order order = new Order(UUID.randomUUID(),USER_1);
        Item item1 = new Item(uuidItem,"nameTest","descriptionTest",10.0,5);
        ItemGroup itemGroup = new ItemGroup(UUID.randomUUID(),item1,item1.getPrice(),3, LocalDate.now().plusDays(1),order);
        ItemGroupCreateDTO itemGroupCreateDTO = new ItemGroupCreateDTO(item1.getId(),3);

        Mockito.when(itemService.getOneItemById(item1.getId())).thenReturn(item1);
        Mockito.when(itemGroupRepository.save(Mockito.any(ItemGroup.class))).thenAnswer(i-> i.getArguments()[0]);

        ItemGroup itemGroupCreated = itemGroupService.createItemGroup(itemGroupCreateDTO,order);

        Assertions.assertThat(itemGroupCreated).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(itemGroup);

    }
    @Test
    public void createItemGroup_givenItemGroupCreateDTOValidAndItemExistAndIsNotAvailable_thenReturnItemGroupWith7DayShipping(){
        UUID uuidItem = UUID.randomUUID();

        Order order = new Order(UUID.randomUUID(),USER_1);
        Item item1 = new Item(uuidItem,"nameTest","descriptionTest",10.0,5);
        ItemGroup itemGroup = new ItemGroup(UUID.randomUUID(),item1,item1.getPrice(),10, LocalDate.now().plusDays(7),order);
        ItemGroupCreateDTO itemGroupCreateDTO = new ItemGroupCreateDTO(item1.getId(),10);

        Mockito.when(itemService.getOneItemById(item1.getId())).thenReturn(item1);
        Mockito.when(itemGroupRepository.save(Mockito.any(ItemGroup.class))).thenAnswer(i-> i.getArguments()[0]);

        ItemGroup itemGroupCreated = itemGroupService.createItemGroup(itemGroupCreateDTO,order);

        Assertions.assertThat(itemGroupCreated).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(itemGroup);


    }



}
