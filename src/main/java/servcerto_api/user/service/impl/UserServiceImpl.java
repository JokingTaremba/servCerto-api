package servcerto_api.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servcerto_api.exception.handlers.client.EntityConflictException;
import servcerto_api.user.model.User;
import servcerto_api.user.repository.UserRepository;
import servcerto_api.user.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public String createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EntityConflictException("There is already a user with this email!");
        }
        userRepository.save(user);
        return "User created successfully!";
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserById(UUID id) {
        return null;
    }

    @Override
    public User updateUser(UUID id, User user) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }
}
