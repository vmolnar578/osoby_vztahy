package school.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import school.service.StudentsService;
import school.entity.StudentsEntity;
import school.dto.StudentsDto;

import java.util.List;

@RestController
public class StudentsController {

    @Resource
    private StudentsService service;

    @PostMapping("/api/students")
    public StudentsEntity addStudent(@RequestBody StudentsDto student) {
        return service.createStudent(student);
    }

    @GetMapping("/api/students")
    public List<StudentsDto> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/api/students/{studentId}")
    public StudentsDto getStudentById(@PathVariable Long studentId) {
        return service.getStudentById(studentId);
    }

    @PutMapping("/api/students/{studentId}")
    public StudentsDto editStudentById(@PathVariable Long studentId, @RequestBody StudentsDto student) {
        return service.editStudentById(studentId, student);
    }

    @DeleteMapping("/api/students/{studentId}")
    public void removeStudentById(@PathVariable Long studentId) {
        service.removeStudentById(studentId);
    }
}
