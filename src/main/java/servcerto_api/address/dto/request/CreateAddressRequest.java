package servcerto_api.address.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequest {
    private String street;
    private String city;
    private String state;
    private String country;
    private String description;
}