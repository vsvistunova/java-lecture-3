package com.example.java_lecture_3.service;

import com.example.java_lecture_3.model.User;
import com.example.java_lecture_3.util.TestDataUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class UserService {
    private final List<User> users = TestDataUtil.createTestUsers();
    public List<User> getAllUsers() {
        return users;
    }
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public List<User> getUsersByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return users.stream()
                .filter(user -> user.getAge() >= minAge && user.getAge()<=maxAge)
                .toList();
    }
    public User createUser(@Validated @RequestBody User newUser) {
        generateId();
        users.add(newUser);
        return newUser;
    }
    private long generateId() {
        return users.stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0L)
                + 1;
    }
    public User updateUser(@PathVariable Long id,@RequestBody User userUpdate){
        User updateUser = getUserById(id);
        if (updateUser != null) {
            updateUser.setName(userUpdate.getName());
            updateUser.setAge(userUpdate.getAge());
            updateUser.setEmail(userUpdate.getEmail());
        }
        return updateUser;
    }
    public boolean deleteUser(@PathVariable Long id){
        if (getUserById(id) == null) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }else{
            users.removeIf(user -> user.getId().equals(id));
            return true;
        }
    }
}
