package school.service.students;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import school.dal.students.StudentsEntity;
import school.dal.students.StudentsRepository;

@Service
public class StudentsService {

    @Resource
    private StudentsRepository studentsRepository;

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public Long createStudent(StudentsDto studentsDto) {
        StudentsEntity student = new StudentsEntity();
        studentsRepository.save(convertToEntity(student, studentsDto));
        return student.getId();
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_TEACHER', 'ROLE_PARENT', 'ROLE_STUDENT')")
    public List<StudentsDto> getAllStudents() {
        return convertToDTOs(studentsRepository.findAll());
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_TEACHER', 'ROLE_PARENT', 'ROLE_STUDENT')")
    public StudentsDto getStudentById(Long id) {
        return convertToDTO(studentsRepository.findById(id).orElse(null));
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public StudentsDto editStudentById(Long personId, StudentsDto studentsDto) {
        StudentsEntity student = studentsRepository.findById(personId).orElse(null);
        if (student == null) return null;

        return convertToDTO(studentsRepository.save(convertToEntity(student, studentsDto)));
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public void removeStudentById(Long id) {
        if (studentsRepository.existsById(id)) {
            studentsRepository.deleteById(id);
        }
    }

    // -------------------------------- Convert Functions -----------------------------------

    private List<StudentsDto> convertToDTOs(List<StudentsEntity> students) {
        List<StudentsDto> studentsDto = new ArrayList<>();
        for (StudentsEntity student: students) {
            studentsDto.add(convertToDTO(student));
        }
        return studentsDto;
    }

    private StudentsDto convertToDTO(StudentsEntity studentsEntity) {
        if (studentsEntity == null) return null;

        StudentsDto student = new StudentsDto();
        student.setId(studentsEntity.getId());
        student.setFirstName(studentsEntity.getFirstName());
        student.setLastName(studentsEntity.getLastName());
        student.setGrade(studentsEntity.getGrade());
        student.setGender(studentsEntity.getGender());
        student.setDateOfBirth(studentsEntity.getDateOfBirth());
        student.setLunchId(studentsEntity.getLunchId());
        student.setParentOneId(studentsEntity.getParentOneId());
        student.setParentTwoId(studentsEntity.getParentTwoId());
        student.setTeacherId(studentsEntity.getTeacherId());
        student.setImage(studentsEntity.getImage());
        return student;
    }

    private StudentsEntity convertToEntity(StudentsEntity student, StudentsDto studentsDto) {
        if (studentsDto == null) return null;

        student.setFirstName(studentsDto.getFirstName());
        student.setLastName(studentsDto.getLastName());
        student.setGrade(studentsDto.getGrade());
        student.setGender(studentsDto.getGender());
        student.setDateOfBirth(studentsDto.getDateOfBirth());
        student.setLunchId(studentsDto.getLunchId());
        student.setImage(studentsDto.getImage());
        student.setParentOneId(studentsDto.getParentOneId());
        student.setParentTwoId(studentsDto.getParentTwoId());
        student.setTeacherId(studentsDto.getTeacherId());
        return student;
    }

    // ------------------------------------------------------------------------------------
}
 
