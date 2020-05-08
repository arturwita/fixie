package fixie.activity_service.dto;

import javax.validation.constraints.NotNull;
import java.util.List;


public class ActivityDTO {
    public Long orderId;

    @NotNull(message = "`activities` cannot be empty")
    public List<SingleActivityDTO> activities;
}
