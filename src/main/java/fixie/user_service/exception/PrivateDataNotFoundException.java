package fixie.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "PrivateData not found")
public class PrivateDataNotFoundException extends Exception {
}
