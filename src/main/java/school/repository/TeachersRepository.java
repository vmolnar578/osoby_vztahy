package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.entity.TeachersEntity;

@Repository
public interface TeachersRepository extends JpaRepository<TeachersEntity, Long> {
}