package servcerto_api.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import servcerto_api.user.dto.request.CreateUserRequest;
import servcerto_api.user.dto.request.UpdateUserRequest;
import servcerto_api.user.dto.response.UserResponse;
import servcerto_api.user.repository.UserRepository;
import servcerto_api.user.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse getUserById(UUID id) {
        return null;
    }

    @Override
    public UserResponse updateUser(UUID id, UpdateUserRequest request) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }
}
