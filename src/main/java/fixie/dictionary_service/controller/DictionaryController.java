package fixie.dictionary_service.controller;

import fixie.dictionary_service.dto.ActivityDictionaryDTO;
import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.service.DictionaryService;
import fixie.user_service.dto.UserDTO;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DictionaryController {


    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @SneakyThrows
    @PostMapping ("/addPartType")
    public PartType addPartType(@RequestBody PartTypeDTO partTypeDTO){

        return dictionaryService.addPartType(partTypeDTO);
    }

    @SneakyThrows
    @DeleteMapping ("/deletePartType")
    public PartType deletePartType(@RequestBody PartTypeDTO partTypeDTO){

        return dictionaryService.deletePartType(partTypeDTO);
    }

    @SneakyThrows
    @PutMapping ("/updatePartType")
    public PartType updatePartType(@RequestBody PartTypeDTO partTypeDTO) {

        return dictionaryService.updatePartType(partTypeDTO);
    }


    @SneakyThrows
    @GetMapping ("/getPartTypes")
    public List<PartType> getPartTypes() {

        return dictionaryService.getPartTypes();
    }


    @SneakyThrows
    @PostMapping ("/addActivityDictionary")
    public ActivityDictionary addActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){

        return dictionaryService.addActivityDictionary(activityDictionaryDTO);
    }

    @SneakyThrows
    @DeleteMapping ("/deleteActivityDictionary")
    public ActivityDictionary deleteActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO){

        return dictionaryService.deleteActivityDictionary(activityDictionaryDTO);
    }

    @SneakyThrows
    @PutMapping ("/updateActivityDictionary")
    public ActivityDictionary updateActivityDictionary(@RequestBody ActivityDictionaryDTO activityDictionaryDTO) {

        return dictionaryService.updateActivityDictionary(activityDictionaryDTO);
    }


    @SneakyThrows
    @GetMapping ("/getActivityDictionaries")
    public List<ActivityDictionary> getActivityDictionaries() {

        return dictionaryService.getActivityDictionaries();
    }






}
