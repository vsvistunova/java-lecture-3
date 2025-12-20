package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.response.EventRegistrationResponse;
import com.university.java_lecture_3.dto.response.UserSummaryResponse;
import com.university.java_lecture_3.service.EventRegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(
        name = "Регистрации на мероприятия",
        description = "API для управления участием пользователей в мероприятиях: запись, просмотр участников."
)
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<UserSummaryResponse>> getParticipantsByEventId(
            @PathVariable Long eventId,
            @PageableDefault(5) Pageable pageable
    ) {
        return ResponseEntity.ok(eventRegistrationService.getParticipantsByEventId(eventId, pageable));
    }

    @PostMapping("/{eventId}/participants/{userId}")
    public ResponseEntity<EventRegistrationResponse> addParticipant(@PathVariable Long eventId, @PathVariable Long userId) {
        return new ResponseEntity<>(eventRegistrationService.registerUser(eventId, userId), HttpStatus.CREATED);
    }

}
