package uz.pdp.g30spingbootsecurity.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.DeserializationException;
import io.jsonwebtoken.io.Deserializer;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.g30spingbootsecurity.domain.Role;
import uz.pdp.g30spingbootsecurity.domain.User;

import javax.crypto.SecretKey;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret.key}")
    private String secret;

    @Value("${jwt.token.secret.expiry-mills}")
    private Long expiry;

    public String generateToken(final User user) {
        StringBuilder roles = new StringBuilder();
        user.getRoles().forEach(role -> roles.append(role.getName()).append(','));
        roles.delete(roles.length() - 1, roles.length());
        return Jwts.builder()
                .id(String.valueOf(user.getId()))
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .claim("roles", roles)
                .signWith(key())
                .compact();
    }

    public SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public boolean isValid(String token){
        Claims claims = parseAllClaims(token);
        Date expiryDate = extractExpiryDate(claims);
        return expiryDate.after(new Date());
    }

    public Claims parseAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Date extractExpiryDate(Claims claims){
        return claims.getExpiration();
    }
}
