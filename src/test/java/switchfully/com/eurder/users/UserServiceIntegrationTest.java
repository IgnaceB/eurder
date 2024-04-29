/*
package switchfully.com.eurder.users;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;
import switchfully.com.eurder.exceptions.CustomerNotFoundException;

import java.util.UUID;

import static org.assertj.core.util.Lists.newArrayList;

public class UserServiceIntegrationTest {

    private UserRepository userRepository =new UserRepository();
    private UserMapper userMapper =new UserMapper();


    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    public void Setup(){
        user1 =new User("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1","mdp");
        user2 =new User("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2","mdp");
        userRepository =new UserRepository(newArrayList(user1, user2));
        userService =new UserService(userRepository, userMapper);

    }

    @Test
    public void getAllCustomers_givenCustomerRepositoryIsNotEmpty_thenReturnListOfCustomerDTO(){
        userService =new UserService(new UserRepository(newArrayList(user1, user2)),new UserMapper());

        Assertions.assertThat(userService.getAllCustomers()).containsExactlyInAnyOrder(
                new UserDTO(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getAddress(), user1.getEmailAddress(), user1.getPhoneNumber()),
                new UserDTO(user2.getId(), user2.getFirstName(), user2.getLastName(), user2.getAddress(), user2.getEmailAddress(), user2.getPhoneNumber()));
    }


    @Test
    public void createCustomer_givenCustomerCreateDTOIsFull_thenReturnNewCustomerDTO(){
        UserCreateDTO userCreateDTO = new UserCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789","mdp");

        UserDTO userDTO = userService.createCustomer(userCreateDTO);

        Assertions.assertThat(userDTO.getFirstName()).isEqualTo(userCreateDTO.getFirstName());
        Assertions.assertThat(userDTO.getLastName()).isEqualTo(userCreateDTO.getLastName());
        Assertions.assertThat(userDTO.getAddress()).isEqualTo(userCreateDTO.getAddress());
        Assertions.assertThat(userDTO.getEmailAddress()).isEqualTo(userCreateDTO.getEmailAddress());
        Assertions.assertThat(userDTO.getPhoneNumber()).isEqualTo(userCreateDTO.getPhoneNumber());
        Assertions.assertThat(userService.getAllCustomers().getLast()).isEqualTo(userDTO);
    }

    @Test
    public void getOneCustomerById_givenIDExistInCustomerRepository_thenReturnCustomerDTO(){
        Assertions.assertThat(userService.getOneCustomerByID(user1.getId())).isEqualTo(
                new UserDTO(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getAddress(), user1.getEmailAddress(), user1.getPhoneNumber()));
    }

    @Test
    public void getOneCustomerById_givenIDDoesNotExistInCustomerRepository_thenThrowCustomerNotFoundException(){
        UUID fakeId = UUID.randomUUID();

        Assertions.assertThatThrownBy(()-> userService.getOneCustomerByID(fakeId)).isInstanceOf(CustomerNotFoundException.class);
    }


}
*/
