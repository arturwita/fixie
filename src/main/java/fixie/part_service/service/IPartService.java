package fixie.part_service.service;

import fixie.part_service.dto.PartDTO;
import fixie.part_service.entity.Part;
import fixie.part_service.exception.PartNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPartService {

    List<Part> getParts();

    Part addPart(PartDTO partDTO);

    Optional<Part> deletePart(Long id)
            throws PartNotFoundException;
}
