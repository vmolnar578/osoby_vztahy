package school.service.lunches;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import school.dal.lunches.LunchesEntity;
import school.dal.lunches.LunchesRepository;

@Service
public class LunchesService {

    private final LunchesRepository lunchesRepository;

    public LunchesService(LunchesRepository lunchesRepository) {
        this.lunchesRepository = lunchesRepository;

        LunchesEntity lunch = new LunchesEntity();
        String[] mainMeal = {"Kalerab1", "Kalerab2", "Kalerab3", "Kalerab4", "Kalerab5"};
        String[] mainMealImg = {
                "https://zdravopedia.sk/assets/images/Zelenina/kalerab.jpg",
                "https://zdravopedia.sk/assets/images/Zelenina/kalerab.jpg",
                "https://zdravopedia.sk/assets/images/Zelenina/kalerab.jpg",
                "https://zdravopedia.sk/assets/images/Zelenina/kalerab.jpg",
                "https://zdravopedia.sk/assets/images/Zelenina/kalerab.jpg"
        };

        for(int i = 0; i < mainMeal.length; i++) {
            lunch.setId(i+1);
            lunch.setMainMeal(mainMeal[i]);
            lunch.setImage(mainMealImg[i]);
            lunchesRepository.save(lunch);
        }
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public Long createLunch(LunchesDto lunchesDto) {
        LunchesEntity lunch = new LunchesEntity();
        lunchesRepository.save(convertToEntity(lunch, lunchesDto));
        return lunch.getId();
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_TEACHER', 'ROLE_PARENT', 'ROLE_STUDENT')")
    public List<LunchesDto> getAllLunches() {
        return convertToDTOs(lunchesRepository.findAll());
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_TEACHER', 'ROLE_PARENT', 'ROLE_STUDENT')")
    public LunchesDto getLunchById(Long id) {
        return convertToDTO(lunchesRepository.findById(id).orElse(null));
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public LunchesDto editLunchById(Long lunchId, LunchesDto lunchesDto) {
        LunchesEntity lunch = lunchesRepository.findById(lunchId).orElse(null);
        if (lunch == null) return null;

        return convertToDTO(lunchesRepository.save(convertToEntity(lunch, lunchesDto)));
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    public void removeLunchById(Long id) {
        if (lunchesRepository.existsById(id)) {
            lunchesRepository.deleteById(id);
        }
    }

    // -------------------------------- Convert Functions -----------------------------------

    private List<LunchesDto> convertToDTOs(List<LunchesEntity> lunches) {
        List<LunchesDto> lunchesDto = new ArrayList<>();
        for (LunchesEntity lunch: lunches) {
            lunchesDto.add(convertToDTO(lunch));
        }
        return lunchesDto;
    }

    private LunchesDto convertToDTO(LunchesEntity lunchesEntity) {
        if (lunchesEntity == null) return null;

        LunchesDto lunch = new LunchesDto();
        lunch.setId(lunchesEntity.getId());
        lunch.setImage(lunchesEntity.getImage());
        lunch.setMainMeal(lunchesEntity.getMainMeal());
        return lunch;
    }

    private LunchesEntity convertToEntity(LunchesEntity lunch, LunchesDto lunchesDto) {
        if (lunchesDto == null) return null;

        lunch.setImage(lunchesDto.getImage());
        lunch.setMainMeal(lunchesDto.getMainMeal());
        return lunch;
    }

    // ------------------------------------------------------------------------------------
}
 
