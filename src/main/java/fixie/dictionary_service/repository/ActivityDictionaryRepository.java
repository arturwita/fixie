package fixie.dictionary_service.repository;

import fixie.dictionary_service.entity.ActivityDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDictionaryRepository extends JpaRepository<ActivityDictionary, String> {
}
