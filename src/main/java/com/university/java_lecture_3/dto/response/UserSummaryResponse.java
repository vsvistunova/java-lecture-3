package com.university.java_lecture_3.dto.response;

public record UserSummaryResponse(
        Long id,
        String name,
        int age,
        String email,
        String role,
        String groupName,
        int eventsCount
) {
}
