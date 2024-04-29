/*
package switchfully.com.eurder.users;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;
import switchfully.com.eurder.exceptions.CustomerNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void createCustomer_givenCustomerCreateDTO_thenReturnNewCustomerDTO(){
        //Given
        UserCreateDTO userCreateDTO = new UserCreateDTO("firstnameTest","lastNameTest","test avenue 01 - 1000 TEST","email@test.test","0123456789","mdp");
        User user = new User(userCreateDTO.getFirstName(), userCreateDTO.getLastName(), userCreateDTO.getAddress(), userCreateDTO.getEmailAddress(), userCreateDTO.getPhoneNumber(),"mdp");
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmailAddress(), user.getPhoneNumber());

        Mockito.when(userRepository.createCustomer(userCreateDTO)).thenReturn(user);
        Mockito.when(userMapper.toDTO(user)).thenReturn(userDTO);

        Assertions.assertThat(userService.createCustomer(userCreateDTO)).isEqualTo(userDTO);
    }
    @Test
    public void getAllCustomers_givenCustomersInRepository_thenReturnListOfCustomerDTO(){
        User user1 =new User("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1","mdp");
        User user2 =new User("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2","mdp");
        UserDTO userDTO1 = new UserDTO(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getAddress(), user1.getEmailAddress(), user1.getPhoneNumber());
        UserDTO userDTO2 = new UserDTO(user2.getId(), user2.getFirstName(), user2.getLastName(), user2.getAddress(), user2.getEmailAddress(), user2.getPhoneNumber());

        Mockito.when(userRepository.getAllCustomers()).thenReturn(newArrayList(user1, user2));
        Mockito.when(userMapper.toDTO(user1)).thenReturn(userDTO1);
        Mockito.when(userMapper.toDTO(user2)).thenReturn(userDTO2);

        Assertions.assertThat(userService.getAllCustomers()).containsExactlyInAnyOrder(
                userDTO1, userDTO2);
    }
    @Test
    public void getAllCustomers_givenNoInRepository_thenReturnEmptyList(){
        Mockito.when(userRepository.getAllCustomers()).thenReturn(newArrayList());
        Assertions.assertThat(userService.getAllCustomers()).containsExactlyInAnyOrder();
    }
    @Test
    public void getOneCustomerById_givenIdExistInRepository_thenReturnCustomerDTO(){
        User user1 =new User("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1","mdp");
        UserDTO userDTO1 = new UserDTO(user1.getId(), user1.getFirstName(), user1.getLastName(), user1.getAddress(), user1.getEmailAddress(), user1.getPhoneNumber());

        Mockito.when(userRepository.getOneCustomerById(user1.getId())).thenReturn(Optional.of(user1));
        Mockito.when(userMapper.toDTO(user1)).thenReturn(userDTO1);

        Assertions.assertThat(userService.getOneCustomerByID(user1.getId())).isEqualTo(userDTO1);
    }
    @Test
    public void getOneCustomerById_givenIdDoesntExistInRepository_thenThrowCustomerNotFoundException(){
        UUID fakeId = UUID.randomUUID();
        Mockito.when(userRepository.getOneCustomerById(fakeId)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(()-> userService.getOneCustomerByID(fakeId)).isInstanceOf(CustomerNotFoundException.class);
    }
}*/
