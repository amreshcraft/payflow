package amreshmaurya.com.payflow.controller;

import amreshmaurya.com.payflow.api.ApiResponse;
import amreshmaurya.com.payflow.dto.user.LoginUser;
import amreshmaurya.com.payflow.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginUser loginUser) {
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(),loginUser.getPassword()));
       if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginUser.getEmail());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
