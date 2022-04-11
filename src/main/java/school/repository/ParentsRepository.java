package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.entity.ParentsEntity;

@Repository
public interface ParentsRepository extends JpaRepository<ParentsEntity, Long> {
}