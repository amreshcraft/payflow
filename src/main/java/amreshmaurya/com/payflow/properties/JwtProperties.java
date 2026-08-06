package amreshmaurya.com.payflow.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties() {
    static String secret;
    static Long expiration;
}
