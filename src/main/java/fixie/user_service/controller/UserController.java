package fixie.user_service.controller;

import fixie.user_service.entity.User;
import lombok.SneakyThrows;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @SneakyThrows
    @PostMapping("/grantRole")
    public User grantRole(@RequestHeader String token, @RequestBody UserDTO user) {
        return userService.grantRole(token, user);
    }

//    TODO: updateUser
//    @PutMapping("/updateUser")
//    public String updateUser(@RequestHeader String token, @RequestBody UserDTO user) {
//        return userService.updateUser(token, user);
//    }
}
