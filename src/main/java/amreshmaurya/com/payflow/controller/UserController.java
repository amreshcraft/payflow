package amreshmaurya.com.payflow.controller;

import java.util.UUID;

import amreshmaurya.com.payflow.api.ApiResponse;
import amreshmaurya.com.payflow.dto.user.CreateUser;
import amreshmaurya.com.payflow.dto.user.UpdateUser;
import amreshmaurya.com.payflow.dto.user.UserResponse;
import amreshmaurya.com.payflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody CreateUser createUser) {

        UserResponse userResponse = userService.createUser(createUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<UserResponse>builder()
                                .data(userResponse)
                                .success(true)
                                .message("User created successfully!")
                                .build()
                );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @PathVariable UUID id) {

        UserResponse userResponse = userService.getUserById(id);

        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .data(userResponse)
                        .success(true)
                        .message("User found successfully!")
                        .build()
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUser updateUser) {

        UserResponse userResponse = userService.updateUser(id, updateUser);

        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .data(userResponse)
                        .success(true)
                        .message("User updated successfully!")
                        .build()
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable UUID id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .data(null)
                        .success(true)
                        .message("User deleted successfully!")
                        .build()
        );
    }
}
