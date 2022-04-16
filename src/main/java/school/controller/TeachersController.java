package school.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import school.service.TeachersService;
import school.entity.TeachersEntity;
import school.dto.TeachersDto;

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
        return service.getAllTeachers();
    }

    @GetMapping("/api/teachers/{teacherId}")
    public TeachersDto getTeacherById(@PathVariable Long teacherId) {
        return service.getTeacherById(teacherId);
    }

    @PutMapping("/api/teachers/{teacherId}")
    public TeachersDto editTeacherById(@PathVariable Long teacherId, @RequestBody TeachersDto teacher) {
        return service.editTeacherById(teacherId, teacher);
    }

    @DeleteMapping("/api/teachers/{teacherId}")
    public void removeTeacherById(@PathVariable Long teacherId) {
        service.removeTeacherById(teacherId);
    }
}
