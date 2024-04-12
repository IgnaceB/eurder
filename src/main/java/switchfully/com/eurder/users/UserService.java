package switchfully.com.eurder.users;

import org.springframework.stereotype.Service;
import switchfully.com.eurder.users.dto.UserCreateDTO;
import switchfully.com.eurder.users.dto.UserDTO;
import switchfully.com.eurder.exceptions.CustomerNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDTO createCustomer(UserCreateDTO userCreateDTO) {
        return userMapper.toDTO(userRepository.createCustomer(userCreateDTO));
    }

    public List<UserDTO> getAllCustomers() {
    return userRepository.getAllCustomers().stream()
            .map(userMapper::toDTO)
            .collect(Collectors.toList());
    }

    public UserDTO getOneCustomerByID(UUID customerId) {
        return userMapper.toDTO(
                userRepository.getOneCustomerById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Can't find any customer with this ID")));


    }
}
