package fixie.dictionary_service.service;

import fixie.common.InternalApiClient;
import fixie.common.Roles;
import fixie.common.exception.UnauthorizedException;
import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.exception.ActivityDictionaryNotFoundException;
import fixie.dictionary_service.exception.PartTypeNotFoundException;
import fixie.dictionary_service.repository.ActivityDictionaryRepository;
import fixie.dictionary_service.repository.PartTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DictionaryService implements IDictionaryService {

    private final PartTypeRepository partTypeRepository;

    private final ActivityDictionaryRepository activityDictionaryRepository;

    private InternalApiClient apiClient;

    public DictionaryService(PartTypeRepository partTypeRepository, ActivityDictionaryRepository activityRepository) {
        this.partTypeRepository = partTypeRepository;
        this.activityDictionaryRepository = activityRepository;
        this.apiClient = new InternalApiClient();
    }

    @Override
    public List<PartType> getPartTypes() {
        return this.partTypeRepository.findAll();
    }

    @Override
    public PartType addPartType(String token, PartTypeDTO partTypeDTO) throws UnauthorizedException {
        String role = this.apiClient.getRoleFromToken(token);

        if (role == null || !role.equals(Roles.ADMIN)) {
            throw new UnauthorizedException();
        }

        PartType partType = PartType.builder()
                .codeType(partTypeDTO.codeType)
                .nameType(partTypeDTO.nameType)
                .build();

        this.partTypeRepository.save(partType);

        return partType;
    }

    @Override
    public Optional<PartType> deletePartType(String token, String codeType)
            throws UnauthorizedException, PartTypeNotFoundException {
        String role = this.apiClient.getRoleFromToken(token);

        if (role == null || !role.equals(Roles.ADMIN)) {
            throw new UnauthorizedException();
        }

        Optional<PartType> partType = this.partTypeRepository.findById(codeType);

        if (partType.isPresent()) {
            this.partTypeRepository.delete(partType.get());
        } else {
            throw new PartTypeNotFoundException();
        }

        return partType;
    }

    @Override
    public List<ActivityDictionary> getActivityDictionaries() {
        return this.activityDictionaryRepository.findAll();
    }

    @Override
    public ActivityDictionary addActivityDictionary(String token, ActivityDictionaryDTO activityDictionaryDTO)
            throws UnauthorizedException {
        String role = this.apiClient.getRoleFromToken(token);

        if (role == null || !role.equals(Roles.ADMIN)) {
            throw new UnauthorizedException();
        }

        ActivityDictionary activityDictionary = ActivityDictionary.builder()
                .actType(activityDictionaryDTO.actType)
                .actName(activityDictionaryDTO.actName)
                .build();

        this.activityDictionaryRepository.save(activityDictionary);

        return activityDictionary;
    }

    @Override
    public Optional<ActivityDictionary> deleteActivityDictionary(String token, String actType)
            throws UnauthorizedException, ActivityDictionaryNotFoundException {
        String role = this.apiClient.getRoleFromToken(token);

        if (role == null || !role.equals(Roles.ADMIN)) {
            throw new UnauthorizedException();
        }

        Optional<ActivityDictionary> activityDictionary = this.activityDictionaryRepository.findById(actType);

        if (activityDictionary.isPresent()) {
            this.activityDictionaryRepository.delete(activityDictionary.get());
        } else {
            throw new ActivityDictionaryNotFoundException();
        }

        return activityDictionary;
    }
}
