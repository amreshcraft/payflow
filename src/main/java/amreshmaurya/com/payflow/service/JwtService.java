package amreshmaurya.com.payflow.service;


import amreshmaurya.com.payflow.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;




@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(String subject) {
        return generateToken(subject, jwtProperties.getAccessExpiration());
    }

    public String generateRefreshToken(String subject) {
        return generateToken(subject, jwtProperties.getRefreshExpiration());
    }

    private String generateToken(String subject, Long expiration) {
        Date now = new Date();

        return Jwts.builder()
                .subject(subject)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }
}






//
//@Service
//@RequiredArgsConstructor
//public class JwtService {
//    public final JwtProperties jwtProperties;
//    public final AuthenticationManager authenticationManager;
//
//
//    public boolean isExpired(){return false;}
//    public String generateToken(String subject, Long expiration) {
//        Date now = new Date();
//                return Jwts.builder().subject(subject).signWith(getSigningKey()).issuedAt(now).expiration( new Date( now.getTime()+ expiration)).compact();
//    }
//
//    private SecretKey getSigningKey() {
//        return Keys.hmacShaKeyFor(
//                jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)
//        );
//    }
//
//    public String generateAccessToken(String subject) {
//        return generateToken(subject, jwtProperties.getAccessExpiration());
//    }
//
//    public String generateRefreshToken(String subject) {
//        return generateToken(subject, jwtProperties.getRefreshExpiration());
//    }
//    public String extractSubject(String token) {
//        return null;
//    }
//
//
//    public LoginResponse  login(String email) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        email,
//                   null
//                )
//        );
//
//
//        return LoginResponse.builder()
//                .accessToken(generateAccessToken(email))
//                .refreshToken(generateRefreshToken(email))
//                .build();
//
//
//    }
//
//}
//
