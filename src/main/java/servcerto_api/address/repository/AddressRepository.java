package servcerto_api.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import servcerto_api.address.model.Address;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
