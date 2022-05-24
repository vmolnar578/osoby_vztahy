package school.controller;

import org.springframework.web.bind.annotation.*;
import school.service.users.UsersDto;
import school.service.users.UsersService;

import javax.annotation.Resource;

import java.util.List;

@RestController
public class UsersController {
    @Resource
    private UsersService service;

    @PostMapping("/api/users")
    public Long addUser(@RequestBody UsersDto user) {
        return service.createUser(user);
    }

    @GetMapping("/api/users")
    public List<UsersDto> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/api/users/{userId}")
    public UsersDto getUserById(@PathVariable Long userId) {
        return service.getUserById(userId);
    }

    @PutMapping("/api/users/{userId}")
    public UsersDto editUserById(@PathVariable Long userId, @RequestBody UsersDto user) {
        return service.editUserById(userId, user);
    }

    @DeleteMapping("/api/users/{userId}")
    public void removeUserById(@PathVariable Long userId) {
        service.removeUserById(userId);
    }
}
