package servcerto_api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servcerto_api.user.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public boolean existsByEmail(String email);
}
