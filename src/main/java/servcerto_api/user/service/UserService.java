package servcerto_api.user.service;

import servcerto_api.user.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    String createUser(User user);
    List<User> getAllUsers();
    User getUserById(UUID id);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);


}
