package fixie.user_service.controller;

import fixie.user_service.dto.PrivateDataDTO;
import fixie.user_service.entity.PrivateData;
import fixie.user_service.entity.User;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.service.UserService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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

    /*
    ###### PrivateData related
     */

    @SneakyThrows
    @PostMapping("/privateData")
    public PrivateData createPrivateData(@RequestHeader String token, @RequestBody PrivateDataDTO privateDataDTO) {
        return userService.createPrivateData(token, privateDataDTO);
    }

    @SneakyThrows
    @GetMapping("/privateData")
    public List<PrivateData> getUserData(@RequestHeader String token) {
        return userService.getUserData(token);
    }

    @SneakyThrows
    @PutMapping("/privateData/{id}")
    public PrivateData updateUserData(@RequestHeader String token,
                                      @PathVariable Long id,
                                      @RequestBody PrivateDataDTO privateDataDTO) {
        return userService.updatePrivateData(token, id, privateDataDTO);
    }

    @SneakyThrows
    @DeleteMapping("/privateData/{id}")
    public Optional<PrivateData> deletePrivateData(@RequestHeader String token, @PathVariable Long id) {
        return userService.deletePrivateData(token, id);
    }
}
