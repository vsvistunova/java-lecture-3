package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.request.UserRequest;
import com.university.java_lecture_3.dto.response.EventRegistrationResponse;
import com.university.java_lecture_3.dto.response.UserDetailedResponse;
import com.university.java_lecture_3.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.service.EventRegistrationService;
import com.university.java_lecture_3.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(
        name = "Пользователи (Users)",
        description = "API для управления пользователями, включая их регистрации на мероприятия."
)
public class UserController {

    private final UserService userService;
    private final EventRegistrationService eventRegistrationService;

    @GetMapping
    public ResponseEntity<List<UserSummaryResponse>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/{userIdToFind}")
    public ResponseEntity<UserDetailedResponse> getById(@PathVariable Long userIdToFind) {
        return ResponseEntity.ok(userService.getDetailedById(userIdToFind));
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<UserSummaryResponse>> getByAgeBetween(
            @RequestParam Integer minAge, @RequestParam Integer maxAge,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok(userService.getAllByAgeBetween(minAge, maxAge, pageable));
    }

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<EventRegistrationResponse>> getRegistrationsByUserId(
            @PathVariable Long userId,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok(eventRegistrationService.getAllByUserId(userId, pageable));
    }

    @PostMapping
    public ResponseEntity<UserDetailedResponse> create(@Valid @RequestBody UserRequest user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailedResponse> update(
            @PathVariable Long userId,
            @Valid @RequestBody UserRequest updatedUser
    ) {
        return ResponseEntity.ok(userService.update(userId, updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
