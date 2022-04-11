package school.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import school.service.ParentsService;
import school.entity.ParentsEntity;
import school.dto.ParentsDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ParentsController {

    @Resource
    private ParentsService service;

    @PostMapping("/api/parents")
    public ParentsEntity addParent(@RequestBody ParentsDto parent) {
        return service.createParent(parent);
    }

    @GetMapping("/api/parents")
    public List<ParentsDto> getAllParents() {
        return convertToDTOs(service.getAllParents());
    }

    @GetMapping("/api/parents/{parentId}")
    public ParentsDto getParentById(@PathVariable Long parentId) {
        return convertToDTO(service.getParentById(parentId));
    }

    @PutMapping("/api/parents/{parentId}")
    public ParentsDto editParentById(@PathVariable Long parentId, @RequestBody ParentsDto parent) {
        return convertToDTO(service.editParentById(parentId, parent));
    }

    @DeleteMapping("/api/parents/{parentId}")
    public void removeParentById(@PathVariable Long parentId) {
        service.removeParentById(parentId);
    }

    private List<ParentsDto> convertToDTOs(List<ParentsEntity> parents) {
        List<ParentsDto> parentsDto = new ArrayList<ParentsDto>();
        for (ParentsEntity parent: parents) {
            parentsDto.add(convertToDTO(parent));
        }
        return parentsDto;
    }

    private ParentsDto convertToDTO(ParentsEntity parentsEntity) {
        if (parentsEntity == null) return null;

        ParentsDto parent = new ParentsDto();
        parent.setId(parentsEntity.getId());
        parent.setFirstName(parentsEntity.getFirstName());
        parent.setLastName(parentsEntity.getLastName());
        parent.setDateOfBirth(parentsEntity.getDateOfBirth());
        parent.setGender(parentsEntity.getGender());
        return parent;
    }
}
