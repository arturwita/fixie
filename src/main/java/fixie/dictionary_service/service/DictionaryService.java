package fixie.dictionary_service.service;

import fixie.dictionary_service.dto.PartTypeDTO;
import fixie.dictionary_service.entity.PartType;
import fixie.dictionary_service.repository.DictionaryRepository;
import fixie.dictionary_service.repository.PartTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Service
public class DictionaryService implements IDictionaryService {




    private final PartTypeRepository repository;




    public DictionaryService(PartTypeRepository repository) {
        this.repository = repository;

    }

    @Override
    public List<PartType> getPartTypes() {

        return null;
    }

    @Override
    public PartType addPartType(PartTypeDTO partTypeDTO) {


        PartType newType = PartType.builder()
                .codeType(partTypeDTO.codeType)
                .nameType(partTypeDTO.nameType)
                .build();

        this.repository.save(newType);


        return newType;
    }


}
