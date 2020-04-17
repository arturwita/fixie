package fixie.user_service.service;

import fixie.user_service.exception.UserAlreadyExistsException;
import fixie.user_service.exception.UserUnauthorizedException;

public interface IUserService {
    String register(String username, String password) throws UserAlreadyExistsException;
    String login(String username, String password) throws UserUnauthorizedException;
}
