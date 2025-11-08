package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @GetMapping("/filtered")
    public List<User> getUsersByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return userService.getUsersByAge(minAge, maxAge);
    }
    @PostMapping
    public User createUser(@Validated @RequestBody User newUser) {
        return userService.createUser(newUser);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User userUpdate){
        return userService.updateUser(id, userUpdate);
    }
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
