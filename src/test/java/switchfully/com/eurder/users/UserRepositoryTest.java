package switchfully.com.eurder.users;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.util.Lists.newArrayList;


class UserRepositoryTest {

    private UserRepository userRepository;
    private User user1;
    private User user2;

    @BeforeEach
    void beforeEach(){
        user1 =new User("firstNameCustomer1","LastNameCustomer1","emailCustomer1","AddressCustomer1","phoneNumberCustomer1","mdp");
        user2 =new User("firstNameCustomer2","LastNameCustomer2","emailCustomer2","AddressCustomer2","phoneNumberCustomer2","mdp");
        userRepository =new UserRepository(newArrayList(user1, user2));
    }

    @Test
    void getOneCustomerById_givenUUIDExistInRepository_returnOptionalCustomerWithThisID() {
        Assertions.assertThat(userRepository.getOneCustomerById(user1.getId())).isEqualTo(Optional.of(user1));
    }
}