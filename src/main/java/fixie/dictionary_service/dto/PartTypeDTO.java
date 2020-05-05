package fixie.dictionary_service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class PartTypeDTO {

    @NotNull(message = "codeType is required")
    @Size(max = 7, message = "Code type length should be less than 7 characters")
    public String codeType;

    @NotNull(message = "nameType is required")
    @Size(max = 50, message = "Name type length should be less than 50 characters")
    public String nameType;
}
