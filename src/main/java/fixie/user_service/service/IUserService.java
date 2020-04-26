package fixie.user_service.service;

import fixie.user_service.dto.UserDTO;
import fixie.user_service.entity.User;
import fixie.user_service.exception.*;
import org.json.JSONException;

import java.io.IOException;

public interface IUserService {
    String register(String username, String password) throws UserAlreadyExistsException;
    String login(String username, String password) throws UserUnauthorizedException;
    User grantRole(String token, UserDTO userDTO)
            throws UserUnauthorizedException, UserNotFoundException, UnknownRoleException, IOException, BadRequestException, JSONException;
    User changePassword(String token, UserDTO userDTO)
            throws BadRequestException, IOException, JSONException, UserNotFoundException;

}
