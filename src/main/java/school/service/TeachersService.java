package school.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import school.entity.TeachersEntity;
import school.dto.TeachersDto;
import school.repository.TeachersRepository;

@Service
public class TeachersService {

    @Resource
    private TeachersRepository teachersRepository;

    public TeachersEntity createTeacher(TeachersDto teachersDto) {
        TeachersEntity teacher = new TeachersEntity();
        return teachersRepository.save(convertToEntity(teacher, teachersDto));
    }

    public List<TeachersEntity> getAllTeachers() {
        return teachersRepository.findAll();
    }

    public TeachersEntity getTeacherById(Long id) {
        return teachersRepository.findById(id).orElse(null);
    }

    public TeachersEntity editTeacherById(Long personId, TeachersDto teachersDto) {
        TeachersEntity teacher = teachersRepository.findById(personId).orElse(null);
        if (teacher == null) return null;

        return teachersRepository.save(convertToEntity(teacher, teachersDto));
    }

    public void removeTeacherById(Long id) {
        if (teachersRepository.existsById(id)) {
            teachersRepository.deleteById(id);
        }
    }

    private TeachersEntity convertToEntity(TeachersEntity teacher, TeachersDto teachersDto) {
        if (teachersDto == null) return null;

        teacher.setFirstName(teachersDto.getFirstName());
        teacher.setLastName(teachersDto.getLastName());
        teacher.setGender(teachersDto.getGender());
        teacher.setDateOfBirth(teachersDto.getDateOfBirth());
        return teacher;
    }
}
 
