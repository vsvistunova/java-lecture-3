package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.request.UserRequest;
import com.university.java_lecture_3.dto.response.UserDetailedResponse;
import com.university.java_lecture_3.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserSummaryResponse> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/{userIdToFind}")
    public UserDetailedResponse getById(@PathVariable Long userIdToFind) {
        return userService.getDetailedById(userIdToFind);
    }

    @GetMapping("/filtered")
    public List<UserSummaryResponse> getByAgeBetween(
            @RequestParam Integer minAge, @RequestParam Integer maxAge,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return userService.getAllByAgeBetween(minAge, maxAge, pageable);
    }

    @PostMapping
    public UserDetailedResponse create(@Valid @RequestBody UserRequest user) {
        return userService.save(user);
    }

    @PutMapping("/{userId}")
    public UserDetailedResponse update(@PathVariable Long userId, @Valid @RequestBody UserRequest updatedUser) {
        return userService.update(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public boolean delete(@PathVariable Long userId) {
        return userService.delete(userId);
    }

}
