package fixie.dictionary_service.controller;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.service.DictionaryService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class DictionaryController {
    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    /*
    #####
    ##### PartTypes connected methods
    #####
     */

    @SneakyThrows
    @PostMapping("/partTypes")
    public PartType addPartType(@RequestHeader String token, @Valid @RequestBody PartTypeDTO partTypeDTO) {
        return dictionaryService.addPartType(token, partTypeDTO);
    }

    @SneakyThrows
    @DeleteMapping("/partTypes")
    public Optional<PartType> deletePartType(@RequestHeader String token, @RequestParam("codeType") String codeType) {
        return dictionaryService.deletePartType(token, codeType);
    }

    @GetMapping("/partTypes")
    public List<PartType> getPartTypes() {
        return dictionaryService.getPartTypes();
    }

    /*
    #####
    ##### ActivityDictionary connected methods
    #####
     */

    @SneakyThrows
    @PostMapping("/activityDictionary")
    public ActivityDictionary addActivityDictionary(@RequestHeader String token,
                                                    @Valid @RequestBody ActivityDictionaryDTO activityDictionaryDTO) {
        return dictionaryService.addActivityDictionary(token, activityDictionaryDTO);
    }

    @SneakyThrows
    @DeleteMapping("/activityDictionary")
    public Optional<ActivityDictionary> deleteActivityDictionary(@RequestHeader String token,
                                                                 @RequestParam("actType") String actType) {
        return dictionaryService.deleteActivityDictionary(token, actType);
    }

    @GetMapping("/activityDictionary")
    public List<ActivityDictionary> getActivityDictionaries() {
        return dictionaryService.getActivityDictionaries();
    }
}
