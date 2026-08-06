package amreshmaurya.com.payflow.controller;

import amreshmaurya.com.payflow.api.ApiResponse;
import amreshmaurya.com.payflow.dto.user.LoginResponse;
import amreshmaurya.com.payflow.dto.user.LoginUser;
import amreshmaurya.com.payflow.service.AuthService;
import amreshmaurya.com.payflow.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginUser request) {
        System.out.println("Auth controller");
        return ResponseEntity.ok(
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .data(authService.login(request))
                        .build()
        );
    }
}