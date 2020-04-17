package fixie.user_service.controller;

import lombok.SneakyThrows;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @PostMapping("/register")
    public String register(@RequestBody UserDTO user) {
        return userService.register(user.username, user.password);
    }

    @SneakyThrows
    @PostMapping("/login")
    public String login(@RequestBody UserDTO user) {
        return userService.login(user.username, user.password);
    }
}
