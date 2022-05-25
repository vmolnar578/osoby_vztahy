package school.dal.lunches;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchesRepository extends JpaRepository<LunchesEntity, Long> {
}