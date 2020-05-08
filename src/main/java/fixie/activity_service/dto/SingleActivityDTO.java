package fixie.activity_service.dto;

import javax.validation.constraints.NotNull;

public class SingleActivityDTO {
    public String status;
    public Long workerId;

    @NotNull(message = "`partId` is required")
    public Long partId;

    @NotNull(message = "`activityType` is required")
    public String activityType;
}
