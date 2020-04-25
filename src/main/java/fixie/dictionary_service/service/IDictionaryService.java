package fixie.dictionary_service.service;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;


import java.util.List;

public interface IDictionaryService {

    List<PartType> getPartTypes();
    PartType addPartType(PartTypeDTO partTypeDTO);
    PartType deletePartType (PartTypeDTO partTypeDTO);
    PartType updatePartType(PartTypeDTO partTypeDTO);

    List<ActivityDictionary> getActivityDictionaries();
    ActivityDictionary addActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO);
    ActivityDictionary deleteActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO);
    ActivityDictionary updateActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO);


}
