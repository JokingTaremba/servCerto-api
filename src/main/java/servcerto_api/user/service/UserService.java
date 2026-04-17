package servcerto_api.user.service;

import servcerto_api.user.dto.request.CreateUserRequest;
import servcerto_api.user.dto.request.UpdateUserRequest;
import servcerto_api.user.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(UUID id);
    UserResponse updateUser(UUID id, UpdateUserRequest request);
    void deleteUser(UUID id);
}
