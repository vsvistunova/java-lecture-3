package com.university.java_lecture_3.controller;

import com.university.java_lecture_3.dto.response.EventRegistrationResponse;
import com.university.java_lecture_3.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;

    @GetMapping("/participants")
    public List<EventRegistrationResponse> getAllByUserId(
            @RequestParam Long userId,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return eventRegistrationService.getAllByUserId(userId, pageable);
    }

    @PostMapping("/{eventId}/participants/{userId}")
    public EventRegistrationResponse addParticipant(@PathVariable Long eventId, @PathVariable Long userId) {
        return eventRegistrationService.registerUser(eventId, userId);
    }

}
