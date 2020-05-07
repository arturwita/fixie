package fixie.user_service.service;

import java.util.*;
import java.time.Instant;

import fixie.common.InternalApiClient;
import fixie.common.Roles;
import fixie.common.exception.BadRequestException;
import fixie.common.exception.UnauthorizedException;
import fixie.user_service.dto.PrivateDataDTO;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.entity.PrivateData;
import fixie.user_service.exception.*;
import fixie.user_service.repository.PrivateDataRepository;
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

import javax.persistence.EntityNotFoundException;


@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PrivateDataRepository privateDataRepository;

    private final InternalApiClient apiClient;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private Integer expirationTime;

    public UserService(UserRepository userRepository, PrivateDataRepository privateDataRepository) {
        this.userRepository = userRepository;
        this.privateDataRepository = privateDataRepository;
        this.apiClient = new InternalApiClient();
    }


    @Override
    public String register(String username, String password) throws UserAlreadyExistsException {
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            throw new UserAlreadyExistsException();
        }

        user = User.builder()
                .username(username)
                .password(UserServiceUtils.hashPassword(password))
                .role(Roles.CLIENT)
                .build();

        this.userRepository.save(user);

        long now = Instant.now().toEpochMilli();
        return Jwts.builder()
                .setSubject("User")
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .setExpiration(new Date(now + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public String login(String username, String password) throws UnauthorizedException {
        User user = this.userRepository.findByUsername(username);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            throw new UnauthorizedException();
        }

        long now = Instant.now().toEpochMilli();
        return Jwts.builder()
                .setSubject("User")
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("role", user.getRole())
                .setExpiration(new Date(now + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public User grantRole(String token, UserDTO userDTO)
            throws UnauthorizedException, UserNotFoundException, UnknownRoleException {
        String role = this.apiClient.getRoleFromToken(token);

        if (role == null || !role.equals(Roles.ADMIN)) {
            throw new UnauthorizedException();
        }

        User foundUser = this.userRepository.findByUsername(userDTO.username);
        if (foundUser == null) {
            throw new UserNotFoundException();
        }

        List<String> roles = Roles.getPossibleRoles();
        if (!roles.contains(userDTO.role)) {
            throw new UnknownRoleException();
        }

        foundUser.setRole(userDTO.role);
        this.userRepository.save(foundUser);

        return foundUser;
    }

    @Override
    public User changePassword(String token, UserDTO userDTO) throws BadRequestException, UserNotFoundException {
        JSONObject response = this.apiClient.verifyToken(token);
        if (response == null) {
            throw new BadRequestException();
        }

        String username = null;
        try {
            username = response.getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        User foundUser = this.userRepository.findByUsername(username);
        if (foundUser == null) {
            throw new UserNotFoundException();
        }

        String hashedPassword = UserServiceUtils.hashPassword(userDTO.password);
        foundUser.setPassword(hashedPassword);
        this.userRepository.save(foundUser);

        return foundUser;
    }

    @Override
    public PrivateData createPrivateData(String token, PrivateDataDTO privateDataDTO) throws UnauthorizedException {
        String username = this.apiClient.getUsernameFromToken(token);
        User user = this.userRepository.findByUsername(username);

        PrivateData privateData = PrivateData.builder()
                .firstName(privateDataDTO.firstName)
                .lastName(privateDataDTO.lastName)
                .email(privateDataDTO.email)
                .telephone(privateDataDTO.telephone)
                .postalCode(privateDataDTO.postalCode)
                .street(privateDataDTO.street)
                .houseNumber(privateDataDTO.houseNumber)
                .city(privateDataDTO.city)
                .taxNumber(privateDataDTO.taxNumber)
                .user(user)
                .build();

        this.privateDataRepository.save(privateData);

        return privateData;
    }

    @Override
    public List<PrivateData> getUserData(String token) throws UnauthorizedException {
        String username = this.apiClient.getUsernameFromToken(token);
        User user = this.userRepository.findByUsername(username);

        return user.getPrivateDataList();
    }

    @Override
    public PrivateData updatePrivateData(String token, Long id, PrivateDataDTO privateDataDTO) throws UnauthorizedException {
        String username = this.apiClient.getUsernameFromToken(token);
        User user = this.userRepository.findByUsername(username);
        PrivateData privateData = this.privateDataRepository.getOne(id);

        if (!privateData.getUser().getId().equals(user.getId())) throw new UnauthorizedException();

        PrivateData updatedPrivateData = PrivateData.builder()
                .firstName(privateDataDTO.firstName)
                .lastName(privateDataDTO.lastName)
                .email(privateDataDTO.email)
                .telephone(privateDataDTO.telephone)
                .postalCode(privateDataDTO.postalCode)
                .street(privateDataDTO.street)
                .houseNumber(privateDataDTO.houseNumber)
                .city(privateDataDTO.city)
                .taxNumber(privateDataDTO.taxNumber)
                .user(user)
                .id(privateData.getId())
                .build();

        this.privateDataRepository.save(updatedPrivateData);

        return updatedPrivateData;
    }

    @Override
    public Optional<PrivateData> deletePrivateData(String token, Long id) throws UnauthorizedException, EntityNotFoundException, PrivateDataNotFoundException {
        String username = this.apiClient.getUsernameFromToken(token);
        User user = this.userRepository.findByUsername(username);
        Optional<PrivateData> privateDataOptional = this.privateDataRepository.findById(id);

        if (!privateDataOptional.isPresent()) throw new PrivateDataNotFoundException();

        PrivateData privateData = privateDataOptional.get();
        if (!privateData.getUser().getId().equals(user.getId())) throw new UnauthorizedException();

        this.privateDataRepository.delete(privateData);
        return privateDataOptional;
    }
}
