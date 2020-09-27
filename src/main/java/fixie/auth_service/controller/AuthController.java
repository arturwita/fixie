package fixie.auth_service.controller;

import lombok.SneakyThrows;
import io.jsonwebtoken.Claims;
import fixie.auth_service.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @SneakyThrows
    @GetMapping("/verifyToken")
    public Claims verifyToken(@RequestHeader String token) {
        return authService.verifyToken(token);
    }

    @SneakyThrows
    @GetMapping("/refreshToken")
    public String refreshToken(@RequestHeader String token) {
        return authService.refreshToken(token);
    }

}
