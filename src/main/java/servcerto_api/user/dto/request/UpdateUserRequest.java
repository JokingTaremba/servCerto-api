package servcerto_api.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import servcerto_api.address.dto.request.CreateAddressRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String name;
    private String email;
    private String phone;
    private String secondaryPhone;
    private String password;
    private String profileImage;
    private String role;
    private CreateAddressRequest address;
}