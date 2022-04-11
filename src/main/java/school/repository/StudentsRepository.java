package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.entity.StudentsEntity;

@Repository
public interface StudentsRepository extends JpaRepository<StudentsEntity, Long> {
}