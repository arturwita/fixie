package fixie.user_service.service;

import fixie.common.exception.BadRequestException;
import fixie.common.exception.UnauthorizedException;
import fixie.user_service.dto.PrivateDataDTO;
import fixie.user_service.dto.UserDTO;
import fixie.user_service.entity.PrivateData;
import fixie.user_service.entity.User;
import fixie.user_service.exception.*;

import java.util.List;
import java.util.Optional;


public interface IUserService {
    String register(String username, String password) throws UserAlreadyExistsException;

    String login(String username, String password) throws UnauthorizedException;

    User grantRole(String token, UserDTO userDTO)
            throws UnauthorizedException, UserNotFoundException, UnknownRoleException, BadRequestException;

    User changePassword(String token, UserDTO userDTO)
            throws BadRequestException, UserNotFoundException;


    PrivateData createPrivateData(String token, PrivateDataDTO privateDataDTO) throws UnauthorizedException;

    List<PrivateData> getUserData(String token) throws UnauthorizedException;

    PrivateData updatePrivateData(String token, Long id, PrivateDataDTO privateDataDTO) throws UnauthorizedException;

    Optional<PrivateData> deletePrivateData(String token, Long id)
            throws UnauthorizedException, PrivateDataNotFoundException;
}
