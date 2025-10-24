package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userIdToFind}")
    public User getUserById(@PathVariable Long userIdToFind) {
        return userService.findById(userIdToFind);
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
