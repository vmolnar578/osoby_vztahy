package school.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import school.entity.SubjectsEntity;
import school.dto.SubjectsDto;
import school.repository.SubjectsRepository;

@Service
public class SubjectsService {

    @Resource
    private SubjectsRepository subjectsRepository;

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long createSubject(SubjectsDto subjectsDto) {
        SubjectsEntity subject = new SubjectsEntity();
        subjectsRepository.save(convertToEntity(subject, subjectsDto));
        return subject.getId();
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<SubjectsDto> getAllSubjects() {
        return convertToDTOs(subjectsRepository.findAll());
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SubjectsDto getSubjectById(Long id) {
        return convertToDTO(subjectsRepository.findById(id).orElse(null));
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SubjectsDto editSubjectById(Long subjectId, SubjectsDto subjectsDto) {
        SubjectsEntity subject = subjectsRepository.findById(subjectId).orElse(null);
        if (subject == null) return null;

        return convertToDTO(subjectsRepository.save(convertToEntity(subject, subjectsDto)));
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeSubjectById(Long id) {
        if (subjectsRepository.existsById(id)) {
            subjectsRepository.deleteById(id);
        }
    }

    // -------------------------------- Convert Functions -----------------------------------

    private List<SubjectsDto> convertToDTOs(List<SubjectsEntity> subjects) {
        List<SubjectsDto> subjectsDto = new ArrayList<>();
        for (SubjectsEntity subject: subjects) {
            subjectsDto.add(convertToDTO(subject));
        }
        return subjectsDto;
    }

    private SubjectsDto convertToDTO(SubjectsEntity subjectsEntity) {
        if (subjectsEntity == null) return null;

        SubjectsDto subject = new SubjectsDto();
        subject.setId(subjectsEntity.getId());
        subject.setGrade(subjectsEntity.getGrade());
        subject.setTitle(subjectsEntity.getTitle());
        return subject;
    }

    private SubjectsEntity convertToEntity(SubjectsEntity subject, SubjectsDto subjectsDto) {
        if (subjectsDto == null) return null;

        subject.setGrade(subjectsDto.getGrade());
        subject.setTitle(subjectsDto.getTitle());
        return subject;
    }

    // ------------------------------------------------------------------------------------
}
 
