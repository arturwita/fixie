package fixie.auth_service.service;

import fixie.auth_service.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;

public interface IAuthService {
    Claims verifyToken(String token) throws InvalidTokenException;

    String refreshToken(String oldToken) throws InvalidTokenException;
}
