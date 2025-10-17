package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userIdToFind}")
    public User getUserById(@PathVariable Long userIdToFind) {
        Optional<User> optionalUser = userService.findById(userIdToFind);
        return optionalUser.orElse(null);
    }

    @GetMapping("/filtered")
    public List<User> getUsersByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        return userService.findAllByAge(minAge, maxAge);
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.save(newUser);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.update(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }

}
