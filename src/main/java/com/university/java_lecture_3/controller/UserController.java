package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.model.dto.request.CreateUserRequest;
import com.university.java_lecture_3.model.dto.request.UpdateUserRequest;
import com.university.java_lecture_3.model.dto.response.UserDetailedResponse;
import com.university.java_lecture_3.model.dto.response.UserSummaryResponse;
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
    public List<UserSummaryResponse> getAllUsers(
            @RequestParam int pageSize,
            @RequestParam int pageNumber) {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDetailedResponse getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

//    @GetMapping("/filtered")
//    public List<User> getUsersByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
//        return userService.getUsersByAge(minAge, maxAge);
//    }

    @PostMapping
    public UserDetailedResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    public UserDetailedResponse updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(userId, request);
    }

    @GetMapping("/filtered")
    public List<UserSummaryResponse> getUsersByAge(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
        return userService.getUsersByAge(minAge, maxAge);
    }

    @GetMapping("/filtered")
    public List<UserSummaryResponse> getUsersByEmail(@RequestParam String email) {
        return userService.getUsersByEmail(email);
    }

    @GetMapping("/filtered")
    public List<UserSummaryResponse> getUsersByGroupName(@RequestParam String groupName) {
        return userService.getUsersByGroupName(groupName);


    }
//    @DeleteMapping("/{userId}")
//    public boolean deleteUser(@PathVariable Long userId) {
//        return userService.deleteUser(userId);
//    }
}