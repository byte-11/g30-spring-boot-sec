package uz.pdp.g30spingbootsecurity.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.g30spingbootsecurity.domain.User;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret.key}")
    private String secret;

    @Value("${jwt.token.secret.expiry-mills}")
    private Long expiry;

    public String generateToken(final User user) {
        return Jwts.builder()
                .id(String.valueOf(user.getId()))
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .claim("role", user.getRoles())
                .signWith(key())
                .compact();
    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
