package servcerto_api.address.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddressResponse {

    private UUID id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String description;
}
