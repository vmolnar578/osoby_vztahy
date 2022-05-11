package school.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import school.service.parents.ParentsService;
import school.service.parents.ParentsDto;

import java.util.List;

@RestController
public class ParentsController {

    @Resource
    private ParentsService service;

    @PostMapping("/api/parents")
    public Long addParent(@RequestBody ParentsDto parent) {
        return service.createParent(parent);
    }

    @GetMapping("/api/parents")
    public List<ParentsDto> getAllParents() {
        return service.getAllParents();
    }

    @GetMapping("/api/parents/{parentId}")
    public ParentsDto getParentById(@PathVariable Long parentId) {
        return service.getParentById(parentId);
    }

    @PutMapping("/api/parents/{parentId}")
    public ParentsDto editParentById(@PathVariable Long parentId, @RequestBody ParentsDto parent) {
        return service.editParentById(parentId, parent);
    }

    @DeleteMapping("/api/parents/{parentId}")
    public void removeParentById(@PathVariable Long parentId) {
        service.removeParentById(parentId);
    }
}
