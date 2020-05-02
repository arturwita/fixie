package fixie.dictionary_service.service;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.exception.ActivityDictionaryNotFoundException;
import fixie.dictionary_service.exception.PartTypeNotFoundException;
import java.util.List;
import java.util.Optional;

public interface IDictionaryService {

    List<PartType> getPartTypes();
    PartType addPartType(PartTypeDTO partTypeDTO);
    Optional<PartType> deletePartType(PartTypeDTO partTypeDTO) throws PartTypeNotFoundException;
    Optional<PartType> updatePartType(PartTypeDTO partTypeDTO) throws PartTypeNotFoundException;

    List<ActivityDictionary> getActivityDictionaries();
    ActivityDictionary addActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO);
    Optional<ActivityDictionary> deleteActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO)
            throws ActivityDictionaryNotFoundException;
    Optional<ActivityDictionary> updateActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO)
            throws ActivityDictionaryNotFoundException;
}
