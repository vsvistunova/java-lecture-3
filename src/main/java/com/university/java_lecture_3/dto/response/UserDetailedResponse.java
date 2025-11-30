package com.university.java_lecture_3.dto.response;

import java.util.List;

public record UserDetailedResponse(
        Long id,
        String name,
        int age,
        String email,
        String role,
        GroupResponse group,
        List<EventResponse> events
) {
}
