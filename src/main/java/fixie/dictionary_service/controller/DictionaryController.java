package fixie.dictionary_service.controller;

import fixie.dictionary_service.dto.PartTypeDTO;
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
    @GetMapping ("/getPartTypes")
    public List<PartType> getPartTypes() {

        return dictionaryService.getPartTypes();
    }


    @SneakyThrows
    @PostMapping ("/addPartType")
    public PartType addPartType(@RequestBody PartTypeDTO partTypeDTO){


        return dictionaryService.addPartType(partTypeDTO);
    }





}
