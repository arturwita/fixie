package fixie.user_service.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserServiceUtils {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }
}
