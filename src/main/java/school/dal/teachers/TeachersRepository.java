package school.dal.teachers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends JpaRepository<TeachersEntity, Long> {
}