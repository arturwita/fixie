package fixie.user_service.service;

import java.util.Date;
import java.time.Instant;

import io.jsonwebtoken.Jwts;
import fixie.user_service.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import fixie.user_service.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import fixie.user_service.exception.UserUnauthorizedException;
import fixie.user_service.exception.UserAlreadyExistsException;


@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    @Value("${jwt.secret}")
    private String secret;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String register(String username, String password) throws UserAlreadyExistsException {
        User user = this.repository.findByUsername(username);
        if (user != null) {
            throw new UserAlreadyExistsException();
        }

        user = User.builder()
                .username(username)
                .password(BCrypt.hashpw(password, BCrypt.gensalt(10)))
                .build();

        this.repository.save(user);

        long now = Instant.now().toEpochMilli();
        return Jwts.builder()
                .setSubject("User")
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .setExpiration(new Date(now + 10000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public String login(String username, String password) throws UserUnauthorizedException {
        User user = this.repository.findByUsername(username);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new UserUnauthorizedException();
        }

        long now = Instant.now().toEpochMilli();
        return Jwts.builder()
                .setSubject("User")
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .setExpiration(new Date(now + 10000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public String grantRole(String username, String password) {
        return null;
    }
}
