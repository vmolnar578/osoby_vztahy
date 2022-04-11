package school.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
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

    public List<TeachersDto> getAllTeachers() {
        return convertToDTOs(teachersRepository.findAll());
    }

    public TeachersDto getTeacherById(Long id) {
        return convertToDTO(teachersRepository.findById(id).orElse(null));
    }

    public TeachersDto editTeacherById(Long personId, TeachersDto teachersDto) {
        TeachersEntity teacher = teachersRepository.findById(personId).orElse(null);
        if (teacher == null) return null;

        return convertToDTO(teachersRepository.save(convertToEntity(teacher, teachersDto)));
    }

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
        return teacher;
    }

    private TeachersEntity convertToEntity(TeachersEntity teacher, TeachersDto teachersDto) {
        if (teachersDto == null) return null;

        teacher.setFirstName(teachersDto.getFirstName());
        teacher.setLastName(teachersDto.getLastName());
        teacher.setGender(teachersDto.getGender());
        teacher.setDateOfBirth(teachersDto.getDateOfBirth());
        return teacher;
    }

    // ------------------------------------------------------------------------------------
}
 
