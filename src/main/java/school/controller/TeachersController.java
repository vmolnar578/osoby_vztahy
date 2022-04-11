package school.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import school.service.TeachersService;
import school.entity.TeachersEntity;
import school.dto.TeachersDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeachersController {

    @Resource
    private TeachersService service;

    @PostMapping("/api/teachers")
    public TeachersEntity addTeacher(@RequestBody TeachersDto teacher) {
        return service.createTeacher(teacher);
    }

    @GetMapping("/api/teachers")
    public List<TeachersDto> getAllTeachers() {
        return convertToDTOs(service.getAllTeachers());
    }

    @GetMapping("/api/teachers/{teacherId}")
    public TeachersDto getTeacherById(@PathVariable Long teacherId) {
        return convertToDTO(service.getTeacherById(teacherId));
    }

    @PutMapping("/api/teachers/{teacherId}")
    public TeachersDto editTeacherById(@PathVariable Long teacherId, @RequestBody TeachersDto teacher) {
        return convertToDTO(service.editTeacherById(teacherId, teacher));
    }

    @DeleteMapping("/api/teachers/{teacherId}")
    public void removeTeacherById(@PathVariable Long teacherId) {
        service.removeTeacherById(teacherId);
    }

    private List<TeachersDto> convertToDTOs(List<TeachersEntity> teachers) {
        List<TeachersDto> teachersDto = new ArrayList<TeachersDto>();
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
}
