package servcerto_api.user.service.impl;

import servcerto_api.user.model.User;
import servcerto_api.user.service.UserService;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public String createUser(User user) {
        return "";
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
