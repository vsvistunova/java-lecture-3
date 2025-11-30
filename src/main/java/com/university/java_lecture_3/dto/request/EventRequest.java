package com.university.java_lecture_3.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record EventRequest(
        @NotBlank(message = "Title is required")
        @Size(min = 2, max = 128, message = "The title must contain from 2 to 128 characters.")
        String title,

        String description,

        @NotNull(message = "Event time is required")
        @Future(message = "The time of the event should be in the future")
        LocalDateTime eventTime,

        @NotBlank(message = "Location is required")
        @Size(min = 2, max = 256, message = "The location must contain from 2 to 256 characters.")
        String location,

        @Min(value = 0, message = "The maximum number of participants cannot be negative")
        Integer maxParticipants
) {
}
