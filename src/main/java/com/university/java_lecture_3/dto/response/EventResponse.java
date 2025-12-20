package com.university.java_lecture_3.dto.response;

import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        String title,
        String description,
        LocalDateTime eventTime,
        String location,
        int maxParticipants
) {
}
