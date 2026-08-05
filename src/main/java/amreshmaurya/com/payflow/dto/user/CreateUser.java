package amreshmaurya.com.payflow.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUser {
    private String name;
    private String email;
    private String password;
    private String phone;
}
