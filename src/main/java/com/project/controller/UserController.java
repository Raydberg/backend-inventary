package com.project.controller;

import com.project.entity.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        Optional<User> userOpt = service.findOneById(id);
        return userOpt.orElseThrow(null);
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        User createUser = service.create(user);
        return ResponseEntity.ok(createUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        User userUpdate = service.update(id, user);
        return ResponseEntity.ok(userUpdate);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
