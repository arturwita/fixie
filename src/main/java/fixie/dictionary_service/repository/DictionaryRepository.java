package fixie.dictionary_service.repository;

import fixie.dictionary_service.entity.ActivityDictionary;
import fixie.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DictionaryRepository extends JpaRepository<ActivityDictionary,String> {

}