package amreshmaurya.com.payflow.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amreshmaurya.com.payflow.dto.user.CreateUser;
import amreshmaurya.com.payflow.dto.user.UserResponse;
import amreshmaurya.com.payflow.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(CreateUser createUser){
        return userService.createUser(createUser);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

}
