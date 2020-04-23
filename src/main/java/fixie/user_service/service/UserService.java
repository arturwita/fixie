package fixie.user_service.service;

import java.util.Date;
import java.time.Instant;
import java.util.List;

import fixie.common.PossibleRoles;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.exception.UnknownRoleException;
import fixie.user_service.exception.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import fixie.user_service.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import fixie.user_service.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import fixie.user_service.exception.UserUnauthorizedException;
import fixie.user_service.exception.UserAlreadyExistsException;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    private final RestTemplate restTemplate;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${url.decode.token}")
    private String decodeTokenURL;

    public UserService(UserRepository repository, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
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
                .setExpiration(new Date(now + 1200000))
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
                .setExpiration(new Date(now + 1200000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public User grantRole(String token, UserDTO user)
            throws UserUnauthorizedException, UserNotFoundException, UnknownRoleException {
        String url = decodeTokenURL + "?token=" + token;
        String decodedToken = this.restTemplate.getForObject(url, String.class);
        String requestRole = null;

        try {
            JSONObject jsonObject = new JSONObject(decodedToken);
            requestRole = jsonObject.getString("role");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!requestRole.equals(PossibleRoles.ADMIN_MNEMO)) {
            throw new UserUnauthorizedException();
        }

        User foundUser = this.repository.findByUsername(user.username);
        if(foundUser == null) {
            throw new UserNotFoundException();
        }

        List<String> roles = PossibleRoles.getPossibleRoles();
        if(!roles.contains(user.role)) {
            throw new UnknownRoleException();
        }

        foundUser.setRole(user.role);
        this.repository.save(foundUser);

        return foundUser;
    }
}
