package fixie.dictionary_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ActivityDictionary not found")
public class ActivityDictionaryNotFoundException extends Exception {
}
