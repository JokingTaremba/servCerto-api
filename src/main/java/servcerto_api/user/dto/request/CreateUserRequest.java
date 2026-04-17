package servcerto_api.user.dto.request;

import lombok.*;
import servcerto_api.address.dto.request.CreateAddressRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String name;
    private String email;
    private String phone;
    private String secondaryPhone;
    private String password;
    private String profileImage;
    private String role;
    private CreateAddressRequest address;
}