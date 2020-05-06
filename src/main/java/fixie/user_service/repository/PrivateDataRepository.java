package fixie.user_service.repository;

import fixie.user_service.entity.PrivateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateDataRepository extends JpaRepository<PrivateData, Long>{
}
