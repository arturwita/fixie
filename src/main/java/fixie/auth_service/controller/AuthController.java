package fixie.auth_service.controller;

import lombok.SneakyThrows;
import io.jsonwebtoken.Claims;
import fixie.auth_service.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @SneakyThrows
    @GetMapping("/decodeToken")
    public Claims decodeToken(@RequestHeader String token) {
        return authService.decodeToken(token);
    }

//    @SneakyThrows
//    @GetMapping("/refreshToken")
//    public String refreshToken() {
//        return authService.refreshToken();
//    }

}
