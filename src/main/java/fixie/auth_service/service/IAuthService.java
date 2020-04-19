package fixie.auth_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

public interface IAuthService {
    Claims decodeToken(String token) throws SignatureException;
//    String refreshToken() throws SignatureException;
}
