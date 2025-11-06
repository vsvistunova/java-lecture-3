package com.university.java_lecture_3.service;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.TestDataUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final List<User> users = TestDataUtil.createTestUsers();

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long userId) {
        Optional<User> first =  users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();

        return  first
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + userId + " не найден"));
    }

    public List<User> getUsersByAge( Integer minAge,  Integer maxAge) {
        List<User> result = users.stream()
                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new RuntimeException("пользователи с возрастом от " + minAge + " до " + maxAge + " не найдены");
        }
        return result;
    }

    public User createUser( User newUser) {
        users.add(newUser);
        return newUser;
    }

    public User updateUser( Long userId,  User updatedUser) {
        User currentUser = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + userId + " не найден"));

        currentUser.setAge(updatedUser.getAge());
        currentUser.setName(updatedUser.getName());
        currentUser.setEmail(updatedUser.getEmail());
        return currentUser;
    }

    public boolean deleteUser( Long userId) {
        User userToDelete = users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Пользователь с id " + userId + " не найден"));
        users.remove(userToDelete);
        return true;
    }


}


