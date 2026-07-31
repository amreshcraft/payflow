package com.amreshmaurya.payflow.service.user;


import com.amreshmaurya.payflow.entity.user.User;
import com.amreshmaurya.payflow.enums.UserRole;

import java.util.Optional;

public interface UserService {

    User createUser(
            String email,
            String password,
            UserRole role
    );

    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    boolean existsByEmail(String email);

    void enableUser(String email);

    void disableUser(String email);

}
