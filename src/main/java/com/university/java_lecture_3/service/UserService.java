package com.university.java_lecture_3.service;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.TestDataUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = TestDataUtil.createTestUsers();
    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> findUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public User getUserById(Long id) {
        return findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }
    public List<User> getUsersByAge(Integer minAge, Integer maxAge) {
        if (minAge == null || maxAge == null) {
            throw new IllegalArgumentException("Age values cannot be null");
        }
        if (minAge > maxAge) {
            throw new IllegalArgumentException("minAge must be less than or equal to maxAge");
        }

        return users.stream()
                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
                .toList();
    }
    public User createUser(User newUser) {
        newUser.setId(generateId());
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
    public User updateUser(Long id, User userUpdate){
        User updateUser = getUserById(id);
            updateUser.setName(userUpdate.getName());
            updateUser.setAge(userUpdate.getAge());
            updateUser.setEmail(userUpdate.getEmail());
            return updateUser;
    }
    public boolean deleteUser(Long id){
        Optional<User> userOpt = findUserById(id);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
        return users.remove(userOpt.get());
    }
}
