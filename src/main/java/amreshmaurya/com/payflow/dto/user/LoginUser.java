package amreshmaurya.com.payflow.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {

    @NotBlank(message = "Email can not be null")
    private String email;
    @NotBlank(message = "Password can not be null")
    private String password;
}
