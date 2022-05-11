package school.dal.parents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentsRepository extends JpaRepository<ParentsEntity, Long> {
}