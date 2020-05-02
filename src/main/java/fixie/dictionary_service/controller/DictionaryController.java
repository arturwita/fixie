package fixie.dictionary_service.controller;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.service.DictionaryService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class DictionaryController{
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService){
        this.dictionaryService = dictionaryService;
    }

    @PostMapping ("/partTypes")
    public PartType addPartType(@RequestBody PartTypeDTO partTypeDTO){
        return dictionaryService.addPartType(partTypeDTO);
    }

    @SneakyThrows
    @DeleteMapping ("/partTypes")
    public Optional<PartType> deletePartType(@RequestBody PartTypeDTO partTypeDTO){
        return dictionaryService.deletePartType(partTypeDTO);
    }

    @SneakyThrows
    @PutMapping ("/partTypes")
    public Optional<PartType> updatePartType(@RequestBody PartTypeDTO partTypeDTO) {
        return dictionaryService.updatePartType(partTypeDTO);
    }

    @GetMapping ("/partTypes")
    public List<PartType> getPartTypes() {
        return dictionaryService.getPartTypes();
    }

    @PostMapping ("/activityDictionary")
    public ActivityDictionary addActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){
        return dictionaryService.addActivityDictionary(activityDictionaryDTO);
    }

    @SneakyThrows
    @DeleteMapping ("/activityDictionary")
    public Optional<ActivityDictionary> deleteActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){
        return dictionaryService.deleteActivityDictionary(activityDictionaryDTO);
    }

    @SneakyThrows
    @PutMapping ("/activityDictionary")
    public Optional<ActivityDictionary> updateActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){
        return dictionaryService.updateActivityDictionary(activityDictionaryDTO);
    }

    @GetMapping ("/activityDictionary")
    public List<ActivityDictionary> getActivityDictionaries(){
        return dictionaryService.getActivityDictionaries();
    }
}
