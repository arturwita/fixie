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
public class DictionaryController {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @SneakyThrows
    @PostMapping("/partTypes")
    public PartType addPartType(@RequestHeader String token, @RequestBody PartTypeDTO partTypeDTO) {
        return dictionaryService.addPartType(token, partTypeDTO);
    }

    @SneakyThrows
    @DeleteMapping("/partTypes")
    public Optional<PartType> deletePartType(@RequestHeader String token, @RequestBody PartTypeDTO partTypeDTO) {
        return dictionaryService.deletePartType(token, partTypeDTO);
    }

    @SneakyThrows
    @PutMapping("/partTypes")
    public Optional<PartType> updatePartType(@RequestHeader String token, @RequestBody PartTypeDTO partTypeDTO) {
        return dictionaryService.updatePartType(token, partTypeDTO);
    }

    @GetMapping("/partTypes")
    public List<PartType> getPartTypes() {
        return dictionaryService.getPartTypes();
    }



    @SneakyThrows
    @PostMapping("/activityDictionary")
    public ActivityDictionary addActivityDictionary(@RequestHeader String token,
                                                    @RequestBody ActivityDictionaryDTO activityDictionaryDTO) {
        return dictionaryService.addActivityDictionary(token, activityDictionaryDTO);
    }

    @SneakyThrows
    @DeleteMapping("/activityDictionary")
    public Optional<ActivityDictionary> deleteActivityDictionary(@RequestHeader String token,
                                                                 @RequestBody ActivityDictionaryDTO activityDictionaryDTO) {
        return dictionaryService.deleteActivityDictionary(token, activityDictionaryDTO);
    }

    @SneakyThrows
    @PutMapping("/activityDictionary")
    public Optional<ActivityDictionary> updateActivityDictionary(@RequestHeader String token,
                                                                 @RequestBody ActivityDictionaryDTO activityDictionaryDTO) {
        return dictionaryService.updateActivityDictionary(token, activityDictionaryDTO);
    }

    @GetMapping("/activityDictionary")
    public List<ActivityDictionary> getActivityDictionaries() {
        return dictionaryService.getActivityDictionaries();
    }
}
