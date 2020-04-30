package fixie.user_service.service;

import java.util.*;
import java.time.Instant;

import fixie.common.InternalApiClient;
import fixie.common.PossibleRoles;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.exception.*;
import fixie.user_service.utils.UserServiceUtils;
import io.jsonwebtoken.Jwts;
import fixie.user_service.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import fixie.user_service.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;


@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    private InternalApiClient apiClient;

    @Value("${jwt.secret}")
    private String secret;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.apiClient = new InternalApiClient();
    }


    @Override
    public String register(String username, String password) throws UserAlreadyExistsException {
        User user = this.repository.findByUsername(username);
        if (user != null) {
            throw new UserAlreadyExistsException();
        }

        user = User.builder()
                .username(username)
                .password(UserServiceUtils.hashPassword(password))
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
    public User grantRole(String token, UserDTO userDTO)
            throws UserUnauthorizedException, UserNotFoundException, UnknownRoleException, BadRequestException {
        JSONObject response = this.apiClient.decodeTokenRequest(token);
        if (response == null) {
            throw new BadRequestException();
        }

        String permittedRole = null;
        try {
            permittedRole = response.getString("role");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (permittedRole == null || !permittedRole.equals(PossibleRoles.ADMIN_MNEMO)) {
            throw new UserUnauthorizedException();
        }

        User foundUser = this.repository.findByUsername(userDTO.username);
        if(foundUser == null) {
            throw new UserNotFoundException();
        }

        List<String> roles = PossibleRoles.getPossibleRoles();
        if(!roles.contains(userDTO.role)) {
            throw new UnknownRoleException();
        }

        foundUser.setRole(userDTO.role);
        this.repository.save(foundUser);

        return foundUser;
    }

    @Override
    public User changePassword(String token, UserDTO userDTO) throws BadRequestException, UserNotFoundException {
        JSONObject response = this.apiClient.decodeTokenRequest(token);
        if (response == null) {
            throw new BadRequestException();
        }

        String username = null;
        try {
            username = response.getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        User foundUser = this.repository.findByUsername(username);
        if(foundUser == null) {
            throw new UserNotFoundException();
        }

        String hashedPassword = UserServiceUtils.hashPassword(userDTO.password);
        foundUser.setPassword(hashedPassword);
        this.repository.save(foundUser);

        return foundUser;
    }
}
