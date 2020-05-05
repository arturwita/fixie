package fixie.dictionary_service.service;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.exception.ActivityDictionaryNotFoundException;
import fixie.dictionary_service.exception.PartTypeNotFoundException;
import fixie.common.exception.BadRequestException;
import fixie.common.exception.UnauthorizedException;

import java.util.List;
import java.util.Optional;

public interface IDictionaryService {

    List<PartType> getPartTypes();

    PartType addPartType(String token, PartTypeDTO partTypeDTO)
            throws BadRequestException, UnauthorizedException;

    Optional<PartType> deletePartType(String token, String codeType)
            throws BadRequestException, UnauthorizedException, PartTypeNotFoundException;


    List<ActivityDictionary> getActivityDictionaries();

    ActivityDictionary addActivityDictionary(String token, ActivityDictionaryDTO activityDictionaryDTO)
            throws BadRequestException, UnauthorizedException;

    Optional<ActivityDictionary> deleteActivityDictionary(String token, String actType)
            throws BadRequestException, UnauthorizedException, ActivityDictionaryNotFoundException;
}
