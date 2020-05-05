package fixie.dictionary_service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ActivityDictionaryDTO {

    @NotNull(message = "actType is required")
    @Size(max = 7, message = "Activity type length should be less than 7 characters")
    public String actType;

    @NotNull(message = "actName is required")
    @Size(max = 50, message = "Activity name length should be less than 50 characters")
    public String actName;
}
