package school;

import org.springframework.web.bind.annotation.*;

@RestController
public class ParentsController {

    @PostMapping("/api/parents")
    public ParentsDto addParent(@RequestBody ParentsDto parent){
        return parent;
    }

    @GetMapping("/api/parents/{parentId}")
    public ParentsDto editParentById(@PathVariable Integer parentId) {
        // edit THIS!
    }

    @DeleteMapping("/api/customers/{customerId}")
    public void removeParent(@PathVariable Long parentId) {
        // edit THIS!
    }
}
