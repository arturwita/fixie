package fixie.auth_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

public interface IAuthService {
    Claims verifyToken(String token) throws SignatureException;

    String refreshToken(String oldToken) throws SignatureException;
}
