package fixie.auth_service.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import fixie.auth_service.exception.InvalidTokenException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.time.Instant;

@Service
public class AuthService implements IAuthService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    @Override
    public Claims verifyToken(String token) throws InvalidTokenException {
        Claims parsedToken = null;

        try {
            parsedToken = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            e.printStackTrace();
        }

        if (parsedToken == null) {
            throw new InvalidTokenException();
        }

        return parsedToken;
    }

    @Override
    public String refreshToken(String oldToken) throws InvalidTokenException {
        Claims parsedToken = null;

        // will throw Exception if old token is wrong or expired - new token MUST be obtained before old expires
        try {
            parsedToken = this.verifyToken(oldToken);
        } catch (JwtException e) {
            e.printStackTrace();
        }

        if(parsedToken == null) {
            throw new InvalidTokenException();
        }

        long now = Instant.now().toEpochMilli();

        return Jwts.builder()
                .setSubject("username")
                .setExpiration(new Date(now + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
