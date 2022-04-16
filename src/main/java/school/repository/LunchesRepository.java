package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.entity.LunchesEntity;

@Repository
public interface LunchesRepository extends JpaRepository<LunchesEntity, Long> {
}