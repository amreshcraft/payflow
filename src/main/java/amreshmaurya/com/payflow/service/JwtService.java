package amreshmaurya.com.payflow.service;


import amreshmaurya.com.payflow.properties.JwtProperties;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    public final JwtProperties jwtProperties;
    public boolean isExpired(){return false;}
    public String generateToken(String email) {
                return null;
    }
    public String extractToken(String token) {
        return null;
    }




}
