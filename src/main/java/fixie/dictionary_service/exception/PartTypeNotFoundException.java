package fixie.dictionary_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "PartType not found")
public class PartTypeNotFoundException extends Exception {
}
