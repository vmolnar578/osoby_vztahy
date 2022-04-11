package school.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import school.dto.ParentsDto;
import school.entity.ParentsEntity;
import school.repository.ParentsRepository;

@Service
public class ParentsService {

    @Resource
    private ParentsRepository parentsRepository;

    public ParentsEntity createParent(ParentsDto parentsDto) {
        ParentsEntity parent = new ParentsEntity();
        return parentsRepository.save(convertToEntity(parent, parentsDto));
    }

    public List<ParentsEntity> getAllParents() {
        return parentsRepository.findAll();
    }

    public ParentsEntity getParentById(Long id) {
        return parentsRepository.findById(id).orElse(null);
    }

    public ParentsEntity editParentById(Long personId, ParentsDto parentsDto) {
        ParentsEntity parent = parentsRepository.findById(personId).orElse(null);
        if (parent == null) return null;

        return parentsRepository.save(convertToEntity(parent, parentsDto));
    }

    public void removeParentById(Long id) {
        if (parentsRepository.existsById(id)) {
            parentsRepository.deleteById(id);
        }
    }

    private ParentsEntity convertToEntity(ParentsEntity parent, ParentsDto parentsDto) {
        if (parentsDto == null) return null;

        parent.setFirstName(parentsDto.getFirstName());
        parent.setLastName(parentsDto.getLastName());
        parent.setGender(parentsDto.getGender());
        parent.setDateOfBirth(parentsDto.getDateOfBirth());
        return parent;
    }
}
 
