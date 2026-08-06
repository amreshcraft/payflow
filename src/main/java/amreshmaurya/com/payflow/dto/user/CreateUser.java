package amreshmaurya.com.payflow.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUser {

    @NotBlank(message = "Name can not be null")
    private String name;
    @NotBlank(message = "Email can not be null")
    private String email;
    @NotBlank(message = "Password can not be null")
    private String password;
    @NotBlank(message = "Phone can not be null")
    private String phone;
}
