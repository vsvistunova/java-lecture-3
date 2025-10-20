package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.TestDataUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users = TestDataUtil.createTestUsers();

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("/{userIdToFind}")
    public User getUserById(@PathVariable Long userIdToFind) {
        for (User user : users) {
            if (userIdToFind.equals(user.getId())) {
                return user;
            }
        }
        return null;
    }

    @GetMapping("/filtered")
    public List<User> getUsersByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.getAge() >= minAge && user.getAge() <= maxAge) {
                result.add(user);
            }
        }
        return result;
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        if (newUser.getName().isBlank() ||
                newUser.getAge() < 0 ||
                newUser.getEmail().isBlank()) {
            return null;
        }

        boolean unique = false;
        while (!unique) {
            Long newId = Math.abs(new Random().nextLong());
            unique = users.stream()
                    .noneMatch(user -> user.getId() == newId);
            if (unique) {
                newUser.setId(newId);
            }
        }

        users.add(newUser);
        return newUser;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        if (updatedUser.getName().isBlank() ||
                updatedUser.getAge() < 0 ||
                updatedUser.getEmail().isBlank()) {
            return null;
        }
        for (User currentUser : users) {
            if (currentUser.getId().equals(userId)) {
                currentUser.setName(updatedUser.getName());
                currentUser.setAge(updatedUser.getAge());
                currentUser.setEmail(updatedUser.getEmail());
                return updatedUser;
            }
        }
        return null;
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable Long userId){
        User userToDelete = getUserById(userId);
        if (userToDelete == null) {
            return false;
        } else {
            users.remove(userToDelete);
            return true;
        }
    }
}
