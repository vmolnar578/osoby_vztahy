package school.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import school.dto.ParentsDto;
import school.entity.ParentsEntity;
import school.repository.ParentsRepository;

@Service
public class ParentsService {

    @Resource
    private ParentsRepository parentsRepository;

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long createParent(ParentsDto parentsDto) {
        ParentsEntity parent = new ParentsEntity();
        parentsRepository.save(convertToEntity(parent, parentsDto));
        return parent.getId();
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ParentsDto> getAllParents() {
        return convertToDTOs(parentsRepository.findAll());
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ParentsDto getParentById(Long id) {
        return convertToDTO(parentsRepository.findById(id).orElse(null));
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ParentsDto editParentById(Long personId, ParentsDto parentsDto) {
        ParentsEntity parent = parentsRepository.findById(personId).orElse(null);
        if (parent == null) return null;

        return convertToDTO(parentsRepository.save(convertToEntity(parent, parentsDto)));
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeParentById(Long id) {
        if (parentsRepository.existsById(id)) {
            parentsRepository.deleteById(id);
        }
    }

    // -------------------------------- Convert Functions -----------------------------------

    private List<ParentsDto> convertToDTOs(List<ParentsEntity> parents) {
        List<ParentsDto> parentsDto = new ArrayList<>();
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
        parent.setPhoneNumber(parentsEntity.getPhoneNumber());
        parent.setImage(parentsEntity.getImage());
        return parent;
    }

    private ParentsEntity convertToEntity(ParentsEntity parent, ParentsDto parentsDto) {
        if (parentsDto == null) return null;

        parent.setFirstName(parentsDto.getFirstName());
        parent.setLastName(parentsDto.getLastName());
        parent.setGender(parentsDto.getGender());
        parent.setDateOfBirth(parentsDto.getDateOfBirth());
        parent.setPhoneNumber(parentsDto.getPhoneNumber());
        parent.setImage(parentsDto.getImage());
        return parent;
    }

    // ------------------------------------------------------------------------------------
}
 
