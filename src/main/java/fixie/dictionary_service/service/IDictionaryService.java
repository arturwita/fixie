package fixie.dictionary_service.service;

import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.PartType;


import java.util.List;

public interface IDictionaryService {

    List<PartType> getPartTypes();
    PartType addPartType(PartTypeDTO partTypeDTO);
}
