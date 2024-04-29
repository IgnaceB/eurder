package switchfully.com.eurder.users;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import switchfully.com.eurder.security.Role;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;
import switchfully.com.eurder.exceptions.CustomerNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UUID createCustomer(UserCreateDTO userCreateDTO) {
        return userRepository.save(userMapper.createToUser(userCreateDTO, Role.CUSTOMER)).getId();
    }

    public List<UserDTO> getAllCustomers() {
    return userRepository.findAll().stream()
            .filter(user -> user.getRole().equals(Role.CUSTOMER))
            .map(userMapper::toDTO)
            .collect(Collectors.toList());
    }

    public UserDTO getOneCustomerDTOByID(UUID customerId) {
        return userMapper.toDTO(getOneCustomerById(customerId));
    }

    //shoudl recreate a new method to call a user (not a DTO) or should I just call the repository inside my other service ?
    public User getOneCustomerById(UUID customerId){
        return userRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Can't find any customer with this ID"));
    }
}
