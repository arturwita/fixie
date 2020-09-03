package fixie.activity_service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SingleActivityDTO {

    @Size(max = 20, message = "status length should be less than 20 characters")
    public String status;

    public Long workerId;

    @NotNull(message = "`partId` is required")
    public Long partId;

    @NotNull(message = "`activityType` is required")
    public String activityType;
}
