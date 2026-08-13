package amreshmaurya.com.payflow.service;


import amreshmaurya.com.payflow.dto.user.LoginResponse;
import amreshmaurya.com.payflow.dto.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginUser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                    request.getPassword()
                )
        );
        return LoginResponse.builder()
                .accessToken(jwtService.generateAccessToken(request.getEmail()))
                .refreshToken(jwtService.generateRefreshToken(request.getEmail()))
                .build();
    }
}