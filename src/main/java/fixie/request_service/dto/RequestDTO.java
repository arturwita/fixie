package fixie.request_service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class RequestDTO {
    @NotNull(message = "status is required")
    @Size(max = 20, message = "status length should be less than 20 characters")
    public String status;
}
