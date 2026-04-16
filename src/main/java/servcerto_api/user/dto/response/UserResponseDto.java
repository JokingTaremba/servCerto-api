package servcerto_api.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import servcerto_api.address.dto.response.AddressResponseDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String secondaryPhone;
    private String profileImage;
    private String role;
    private AddressResponseDto address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
