package fixie.user_service.service;

import fixie.user_service.dto.UserDTO;
import fixie.user_service.entity.User;
import fixie.user_service.exception.UnknownRoleException;
import fixie.user_service.exception.UserAlreadyExistsException;
import fixie.user_service.exception.UserNotFoundException;
import fixie.user_service.exception.UserUnauthorizedException;

public interface IUserService {
    String register(String username, String password) throws UserAlreadyExistsException;
    String login(String username, String password) throws UserUnauthorizedException;
    User grantRole(String token, UserDTO user)
            throws UserUnauthorizedException, UserNotFoundException, UnknownRoleException;
}
