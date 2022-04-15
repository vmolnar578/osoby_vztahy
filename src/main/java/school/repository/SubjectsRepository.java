package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.entity.SubjectsEntity;

@Repository
public interface SubjectsRepository extends JpaRepository<SubjectsEntity, Long> {
}