package com.example.java_lecture_3.controller;

import com.example.java_lecture_3.model.User;
import com.example.java_lecture_3.util.TestDataUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController("/users")
public class UserController {
    private final List<User> users = TestDataUtil.createTestUsers();
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    @GetMapping("/filtered")
    public List<User> getUsersByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return users.stream()
                .filter(user -> user.getAge() >= minAge && user.getAge()<=maxAge)
                .toList();
    }
}
