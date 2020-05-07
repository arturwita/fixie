package fixie.part_service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class PartDTO {

    @NotNull(message = "partCodeType is required")
    @Size(max = 7, message = "Code type length should be less than 7 characters")
    public String partCodeType;

    @NotNull(message = "name is required")
    @Size(max = 250, message = "Name type length should be less than 250 characters")
    public String name;
}
