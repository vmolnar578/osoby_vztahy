package school.service.teachers;

import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import school.dal.teachers.TeachersEntity;
import school.dal.teachers.TeachersRepository;

@Service
public class TeachersService {

    @Resource
    private TeachersRepository teachersRepository;

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public Long createTeacher(TeachersDto teachersDto) {
        TeachersEntity teacher = new TeachersEntity();
        teachersRepository.save(convertToEntity(teacher, teachersDto));
        return teacher.getId();
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_TEACHER', 'ROLE_PARENT', 'ROLE_STUDENT')")
    public List<TeachersDto> getAllTeachers() {
        return convertToDTOs(teachersRepository.findAll());
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_TEACHER', 'ROLE_PARENT', 'ROLE_STUDENT')")
    public TeachersDto getTeacherById(Long id) {
        return convertToDTO(teachersRepository.findById(id).orElse(null));
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public TeachersDto editTeacherById(Long personId, TeachersDto teachersDto) {
        TeachersEntity teacher = teachersRepository.findById(personId).orElse(null);
        if (teacher == null) return null;

        return convertToDTO(teachersRepository.save(convertToEntity(teacher, teachersDto)));
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public void removeTeacherById(Long id) {
        if (teachersRepository.existsById(id)) {
            teachersRepository.deleteById(id);
        }
    }

    // -------------------------------- Convert Functions -----------------------------------

    private List<TeachersDto> convertToDTOs(List<TeachersEntity> teachers) {
        List<TeachersDto> teachersDto = new ArrayList<>();
        for (TeachersEntity teacher: teachers) {
            teachersDto.add(convertToDTO(teacher));
        }
        return teachersDto;
    }

    private TeachersDto convertToDTO(TeachersEntity teachersEntity) {
        if (teachersEntity == null) return null;

        TeachersDto teacher = new TeachersDto();
        teacher.setId(teachersEntity.getId());
        teacher.setFirstName(teachersEntity.getFirstName());
        teacher.setLastName(teachersEntity.getLastName());
        teacher.setDateOfBirth(teachersEntity.getDateOfBirth());
        teacher.setGender(teachersEntity.getGender());
        teacher.setLunchId(teachersEntity.getLunchId());
        teacher.setPhoneNumber(teachersEntity.getPhoneNumber());
        teacher.setImage(teachersEntity.getImage());
        return teacher;
    }

    private TeachersEntity convertToEntity(TeachersEntity teacher, TeachersDto teachersDto) {
        if (teachersDto == null) return null;

        teacher.setFirstName(teachersDto.getFirstName());
        teacher.setLastName(teachersDto.getLastName());
        teacher.setGender(teachersDto.getGender());
        teacher.setDateOfBirth(teachersDto.getDateOfBirth());
        teacher.setLunchId(teachersDto.getLunchId());
        teacher.setPhoneNumber(teachersDto.getPhoneNumber());
        teacher.setImage(teachersDto.getImage());
        return teacher;
    }

    // ------------------------------------------------------------------------------------
}
 
