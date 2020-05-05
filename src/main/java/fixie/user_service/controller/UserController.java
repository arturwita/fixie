package fixie.user_service.controller;

import fixie.user_service.dto.PrivateDataDTO;
import fixie.user_service.entity.PrivateData;
import fixie.user_service.entity.User;
import lombok.SneakyThrows;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @SneakyThrows
    @PatchMapping("/grantRole")
    public User grantRole(@RequestHeader String token, @RequestBody UserDTO userDTO) {
        return userService.grantRole(token, userDTO);
    }

    @SneakyThrows
    @PatchMapping("/changePassword")
    public User changePassword(@RequestHeader String token, @RequestBody UserDTO userDTO) {
        return userService.changePassword(token, userDTO);
    }

    @SneakyThrows
    @PostMapping("/privateData")
    public PrivateData createPrivateData(@RequestHeader String token, @RequestBody PrivateDataDTO privateDataDTO) {
        return userService.createPrivateData(token, privateDataDTO);
    }
}
