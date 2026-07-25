package com.amreshmaurya.payflow.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

// POST   /api/v1/auth/register
public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
    // Implementation for user registration
}

// POST   /api/v1/auth/login
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    // Implementation for user login
}
// POST   /api/v1/auth/refresh-token
public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
    // Implementation for refreshing JWT token
}
// POST   /api/v1/auth/logout
public ResponseEntity<?> logoutUser(@RequestBody LogoutRequest logoutRequest) {
    // Implementation for user logout
}

// POST   /api/v1/auth/forgot-password
public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    // Implementation for initiating password reset
}
// POST   /api/v1/auth/reset-password
public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
    // Implementation for resetting password
}
// POST   /api/v1/auth/change-password
public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
    // Implementation for changing password


}

}