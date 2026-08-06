package amreshmaurya.com.payflow.controller;

import java.util.UUID;

import amreshmaurya.com.payflow.api.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody CreateUser createUser){
        return  ResponseEntity.status(HttpStatus.CREATED).body( ApiResponse.<UserResponse>builder().data(userService.createUser(createUser)).success(true).message("User created successfully!").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@Valid @PathVariable UUID id){
        return  ResponseEntity.ok(ApiResponse.<UserResponse>builder().data(userService.getUserById(id)).success(true).message("User Found Successfully").build());
    }

}
