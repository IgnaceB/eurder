package switchfully.com.eurder.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.users.dto.UserCreateDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
/*    private List<User> users;
    private static final User ADMIN = new User(UUID.fromString("33f10c8b-7795-4fbc-adc3-cdea73f4fd4e"),"admin","admin","admin@admin","addressAdmin","dontCall","mdp",Role.ADMIN);

    public UserRepository() {
        this.users =newArrayList(ADMIN);
    }

    public UserRepository(List<User> users) {
        this.users = users;
    }

    public User createCustomer(UserCreateDTO userCreateDTO) {
        User newUser = new User(userCreateDTO.getFirstName(),
                userCreateDTO.getLastName(),
                userCreateDTO.getAddress(),
                userCreateDTO.getEmailAddress(),
                userCreateDTO.getPhoneNumber(),
                userCreateDTO.getPassword());
        this.users.add(newUser);
        return newUser;
    }

    public List<User> getAllCustomers() {
        return this.users.stream()
                .filter(user -> user.getRole().equals(Role.CUSTOMER))
                .collect(Collectors.toList());
    }

    public Optional<User> getOneCustomerById(UUID customerId) {
        return this.users.stream()
                .filter(customer -> customer.getId().equals(customerId))
                .findFirst();
    }*/
}
