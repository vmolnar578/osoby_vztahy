package school.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import school.service.LunchesService;
import school.entity.LunchesEntity;
import school.dto.LunchesDto;

import java.util.List;

@RestController
public class LunchesController {

    @Resource
    private LunchesService service;

    @PostMapping("/api/lunches")
    public LunchesEntity addLunch(@RequestBody LunchesDto lunch) {
        return service.createLunch(lunch);
    }

    @GetMapping("/api/lunches")
    public List<LunchesDto> getAllLunches() {
        return service.getAllLunches();
    }

    @GetMapping("/api/lunches/{lunchId}")
    public LunchesDto getLunchById(@PathVariable Long lunchId) {
        return service.getLunchById(lunchId);
    }

    @PutMapping("/api/lunches/{lunchId}")
    public LunchesDto editLunchById(@PathVariable Long lunchId, @RequestBody LunchesDto lunch) {
        return service.editLunchById(lunchId, lunch);
    }

    @DeleteMapping("/api/lunches/{lunchId}")
    public void removeLunchById(@PathVariable Long lunchId) {
        service.removeLunchById(lunchId);
    }
}
