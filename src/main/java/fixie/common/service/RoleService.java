package fixie.common.service;

import fixie.common.InternalApiClient;
import fixie.common.exception.ForbiddenException;
import fixie.common.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final InternalApiClient apiClient = new InternalApiClient();

    public void checkTokenRole(String token, String role) throws UnauthorizedException, ForbiddenException {
        String tokenRole = this.apiClient.getRoleFromToken(token);
        if (!tokenRole.equals(role)) throw new ForbiddenException();
    }
}
