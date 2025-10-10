package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.TestDataUtil;
import java.util.ArrayList;
import java.util.List;
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
  public List<User> getAllUsers() {
    return users;
  }

  @GetMapping("/{userIdToFind}")
  public User getUserById(@PathVariable Long userIdToFind) {
    for (User user : users) {
      if (user.getId().equals(userIdToFind)) {
        return user;
      }
    }
    return null; // не нашли пользователя с таким id
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
    users.add(newUser);
    return newUser;
  }

  @PutMapping("/{userId}")
  public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
    // находим пользователя по id
    for (User currentUser : users) {
      if (currentUser.getId().equals(userId)) {
        // нашли - обновляем
        currentUser.setAge(updatedUser.getAge());
        currentUser.setName(updatedUser.getName());
        currentUser.setEmail(updatedUser.getEmail());
        return currentUser;
      }
    }

    return null; // не нашли пользователя
  }

  @DeleteMapping("/{userId}")
  public boolean deleteUser(@PathVariable Long userId) {
    // используем метод, который написали ранее, чтобы найти пользователя
    User userToDelete = getUserById(userId);
    if (userToDelete == null) {
      // пользователь не найден - удалить не можем, вернем false
      return false;
    } else {
      users.remove(userToDelete);
      return true;
    }
  }
}
