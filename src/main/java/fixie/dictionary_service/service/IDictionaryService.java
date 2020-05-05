package fixie.dictionary_service.service;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.exception.ActivityDictionaryNotFoundException;
import fixie.dictionary_service.exception.PartTypeNotFoundException;
import fixie.user_service.exception.BadRequestException;
import fixie.user_service.exception.UserUnauthorizedException;

import java.util.List;
import java.util.Optional;

public interface IDictionaryService {

    List<PartType> getPartTypes();

    PartType addPartType(String token, PartTypeDTO partTypeDTO)
            throws BadRequestException, UserUnauthorizedException;

    Optional<PartType> deletePartType(String token, String codeType)
            throws BadRequestException, UserUnauthorizedException, PartTypeNotFoundException;


    List<ActivityDictionary> getActivityDictionaries();

    ActivityDictionary addActivityDictionary(String token, ActivityDictionaryDTO activityDictionaryDTO)
            throws BadRequestException, UserUnauthorizedException;

    Optional<ActivityDictionary> deleteActivityDictionary(String token, String actType)
            throws BadRequestException, UserUnauthorizedException, ActivityDictionaryNotFoundException;
}
