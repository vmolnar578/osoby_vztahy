package school.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import school.service.subjects.SubjectsService;
import school.service.subjects.SubjectsDto;

import java.util.List;

@RestController
public class SubjectsController {

    @Resource
    private SubjectsService service;

    @PostMapping("/api/subjects")
    public Long addSubject(@RequestBody SubjectsDto subject) {
        return service.createSubject(subject);
    }

    @GetMapping("/api/subjects")
    public List<SubjectsDto> getAllSubjects() {
        return service.getAllSubjects();
    }

    @GetMapping("/api/subjects/{subjectId}")
    public SubjectsDto getSubjectById(@PathVariable Long subjectId) {
        return service.getSubjectById(subjectId);
    }

    @PutMapping("/api/subjects/{subjectId}")
    public SubjectsDto editSubjectById(@PathVariable Long subjectId, @RequestBody SubjectsDto subject) {
        return service.editSubjectById(subjectId, subject);
    }

    @DeleteMapping("/api/subjects/{subjectId}")
    public void removeSubjectById(@PathVariable Long subjectId) {
        service.removeSubjectById(subjectId);
    }
}
