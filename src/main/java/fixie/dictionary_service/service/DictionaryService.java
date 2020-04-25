package fixie.dictionary_service.service;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.repository.ActivityDictionaryRepository;
import fixie.dictionary_service.repository.PartTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService implements IDictionaryService {

    private final PartTypeRepository partTypeRepository;
    private final ActivityDictionaryRepository activityDictionaryRepository;

    public DictionaryService(PartTypeRepository partTypeRepository, ActivityDictionaryRepository activityRepository) {
        this.partTypeRepository = partTypeRepository;
        this.activityDictionaryRepository = activityRepository;
    }

    @Override
    public List<PartType> getPartTypes() {

        return this.partTypeRepository.findAll();
    }

    @Override
    public PartType addPartType(PartTypeDTO partTypeDTO) {


        PartType partType = PartType.builder()
                .codeType(partTypeDTO.codeType)
                .nameType(partTypeDTO.nameType)
                .build();

        this.partTypeRepository.save(partType);


        return partType;
    }

    @Override
    public PartType deletePartType(PartTypeDTO partTypeDTO)  {


        PartType partType = PartType.builder()
                .codeType(partTypeDTO.codeType)
                .nameType(partTypeDTO.nameType)
                .build();

        this.partTypeRepository.delete(partType);

        return partType;
    }

    //todo
    @Override
    public PartType updatePartType(PartTypeDTO partTypeDTO) {

        PartType partType = PartType.builder()
                .codeType(partTypeDTO.codeType)
                .nameType(partTypeDTO.nameType)
                .build();



        return partType;
    }



    @Override
    public List<ActivityDictionary> getActivityDictionaries() {

        return this.activityDictionaryRepository.findAll();

    }

    @Override
    public ActivityDictionary addActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO) {


        ActivityDictionary activityDictionary = ActivityDictionary.builder()
                .actType(activityDictionaryDTO.actType)
                .actName(activityDictionaryDTO.actName)
                .build();

        this.activityDictionaryRepository.save(activityDictionary);


        return activityDictionary;
    }

    @Override
    public ActivityDictionary deleteActivityDictionary(ActivityDictionaryDTO activityDictionaryDTO)  {
        ActivityDictionary activityDictionary = ActivityDictionary.builder()
                .actType(activityDictionaryDTO.actType)
                .actName(activityDictionaryDTO.actName)
                .build();

        this.activityDictionaryRepository.delete(activityDictionary);

        return activityDictionary;
    }

    //todo
    @Override
    public ActivityDictionary updateActivityDictionary(ActivityDictionaryDTO ActivityDictionaryDTO) {

        ActivityDictionary activityDictionary = ActivityDictionary.builder()
                .actType(ActivityDictionaryDTO.actType)
                .actName(ActivityDictionaryDTO.actName)
                .build();

        return activityDictionary;
    }




}
