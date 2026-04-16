package servcerto_api.user.dto.request;

import lombok.*;
import servcerto_api.address.dto.request.CreateAddressRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    private String name;
    private String email;
    private String phone;
    private String secondaryPhone;
    private String password;
    private String profileImage;
    private String role;
    private CreateAddressRequestDto address;
}