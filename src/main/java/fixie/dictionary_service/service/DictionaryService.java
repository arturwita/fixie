package fixie.dictionary_service.service;

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

    public DictionaryService(PartTypeRepository partTypeRepository, ActivityDictionaryRepository activityRepository) {
        this.partTypeRepository = partTypeRepository;
        this.activityDictionaryRepository = activityRepository;
    }

    @Override
    public List<PartType> getPartTypes(){
        return this.partTypeRepository.findAll();
    }

    @Override
    public PartType addPartType(PartTypeDTO partTypeDTO){
        PartType partType = PartType.builder()
                .codeType(partTypeDTO.codeType)
                .nameType(partTypeDTO.nameType)
                .build();

        this.partTypeRepository.save(partType);

        return partType;
    }

    @Override
    public Optional<PartType> deletePartType(PartTypeDTO partTypeDTO) throws PartTypeNotFoundException{
        Optional<PartType> partType = this.partTypeRepository.findById(partTypeDTO.codeType);

        if(partType.isPresent()){
            this.partTypeRepository.delete(partType.get());
        }
        else
           throw new PartTypeNotFoundException();

        return partType;
    }

    @Override
    public Optional<PartType> updatePartType(PartTypeDTO partTypeDTO) throws PartTypeNotFoundException{
        Optional<PartType> partType = this.partTypeRepository.findById(partTypeDTO.codeType);

        if(partType.isPresent()){
            if(partTypeDTO.nameType != null){
                partType.get().setNameType(partTypeDTO.nameType);
            }
            this.partTypeRepository.save(partType.get());
        }
        else{
            throw new PartTypeNotFoundException();
        }

        return partType;
    }

    @Override
    public List<ActivityDictionary> getActivityDictionaries(){
        return this.activityDictionaryRepository.findAll();
    }

    @Override
    public ActivityDictionary addActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO){
        ActivityDictionary activityDictionary = ActivityDictionary.builder()
                .actType(activityDictionaryDTO.actType)
                .actName(activityDictionaryDTO.actName)
                .build();

        this.activityDictionaryRepository.save(activityDictionary);

        return activityDictionary;
    }

    @Override
    public Optional<ActivityDictionary> deleteActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO) throws ActivityDictionaryNotFoundException{
        Optional<ActivityDictionary> activityDictionary = this.activityDictionaryRepository.findById(activityDictionaryDTO.actType);

        if(activityDictionary.isPresent()){
            this.activityDictionaryRepository.delete(activityDictionary.get());
        }
        else{
            throw new ActivityDictionaryNotFoundException();
        }

        return activityDictionary;
    }

    @Override
    public Optional<ActivityDictionary> updateActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO) throws ActivityDictionaryNotFoundException{
        Optional<ActivityDictionary> activityDictionary = this.activityDictionaryRepository.findById(activityDictionaryDTO.actType);

        if(activityDictionary.isPresent()){
            if(activityDictionaryDTO.actName != null){
                activityDictionary.get().setActName(activityDictionaryDTO.actName);
            }
            this.activityDictionaryRepository.save(activityDictionary.get());
        }
        else{
            throw new ActivityDictionaryNotFoundException();
        }

        return activityDictionary;
    }
}
