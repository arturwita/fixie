package fixie.auth_service.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.time.Instant;

@Service
public class AuthService implements IAuthService {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Claims verifyToken(String token) throws SignatureException {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    @Override
    public String refreshToken(String oldToken) throws SignatureException {
        long now = Instant.now().toEpochMilli();

        // will throw Exception if old token is wrong or expired - new token MUST be obtained before old expires
        this.verifyToken(oldToken);

        return Jwts.builder()
                .setSubject("username")
                .setExpiration(new Date(now + 10000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
