package com.university.java_lecture_3.dto.response;

public record EventRegistrationResponse(
        Long id,
        UserSummaryResponse user,
        EventResponse event
) {
}
