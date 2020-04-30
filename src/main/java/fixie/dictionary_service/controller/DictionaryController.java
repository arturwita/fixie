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

    @PostMapping ("/addPartType")
    public PartType addPartType(@RequestBody PartTypeDTO partTypeDTO){
        return dictionaryService.addPartType(partTypeDTO);
    }

    @SneakyThrows
    @DeleteMapping ("/deletePartType")
    public Optional<PartType> deletePartType(@RequestBody PartTypeDTO partTypeDTO){
        return dictionaryService.deletePartType(partTypeDTO);
    }

    @SneakyThrows
    @PutMapping ("/updatePartType")
    public Optional<PartType> updatePartType(@RequestBody PartTypeDTO partTypeDTO) {
        return dictionaryService.updatePartType(partTypeDTO);
    }

    @GetMapping ("/getPartTypes")
    public List<PartType> getPartTypes() {
        return dictionaryService.getPartTypes();
    }

    @PostMapping ("/addActivityDictionary")
    public ActivityDictionary addActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){
        return dictionaryService.addActivityDictionary(activityDictionaryDTO);
    }

    @SneakyThrows
    @DeleteMapping ("/deleteActivityDictionary")
    public Optional<ActivityDictionary> deleteActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){
        return dictionaryService.deleteActivityDictionary(activityDictionaryDTO);
    }

    @SneakyThrows
    @PutMapping ("/updateActivityDictionary")
    public Optional<ActivityDictionary> updateActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){
        return dictionaryService.updateActivityDictionary(activityDictionaryDTO);
    }


    @GetMapping ("/getActivityDictionaries")
    public List<ActivityDictionary> getActivityDictionaries(){
        return dictionaryService.getActivityDictionaries();
    }
}
