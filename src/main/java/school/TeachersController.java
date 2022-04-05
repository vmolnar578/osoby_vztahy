package school;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TeachersController {

    @PostMapping("/api/teachers")
    public TeachersDto createTeacher(@RequestBody TeachersDto teacher){
        // DO something!
    }

    @GetMapping("/api/teachers")
    public List<TeachersDto> getTeachers() {
        // DO something!
    }

    @GetMapping("/api/teachers/{teacherId}")
    public TeachersDto getTeacherById(@PathVariable Long teacherId){
        // DO something!
    }

    @DeleteMapping("/api/teachers/{teacherId}")
    public void removeTeacher(@PathVariable Long teacherId){

    }
}
