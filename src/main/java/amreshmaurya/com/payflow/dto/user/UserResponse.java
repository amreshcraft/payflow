package amreshmaurya.com.payflow.dto.user;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String phone;
}
