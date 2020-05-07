package fixie.part_service.service;

import fixie.part_service.dto.PartDTO;
import fixie.part_service.entity.Part;
import fixie.part_service.exception.PartNotFoundException;
import fixie.part_service.repository.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService implements IPartService {
    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> getParts() {
        return partRepository.findAll();
    }

    @Override
    public Part addPart(PartDTO partDTO) {
        Part part = Part.builder()
                .partCodeType(partDTO.partCodeType)
                .name(partDTO.name)
                .build();

        partRepository.save(part);

        return part;
    }

    @Override
    public Optional<Part> deletePart(Long id) throws PartNotFoundException {
        Optional<Part> partType = partRepository.findById(id);

        if (!partType.isPresent()) throw new PartNotFoundException();

        partRepository.delete(partType.get());

        return partType;
    }
}
