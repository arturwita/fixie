package fixie.dictionary_service.repository;

import fixie.dictionary_service.entity.PartType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTypeRepository extends JpaRepository<PartType, String> {
}
