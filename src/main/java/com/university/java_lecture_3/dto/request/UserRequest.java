package com.university.java_lecture_3.dto.request;

import jakarta.validation.constraints.*;

public record UserRequest(
        @NotBlank(message = "The user's name is required")
        @Size(min = 2, max = 64, message = "The user name must contain from 2 to 64 characters.")
        String name,

        @NotNull(message = "Age is required")
        @Min(value = 0, message = "Age cannot be negative")
        @Max(value = 150, message = "The age may not exceed 150 years")
        Integer age,

        @NotBlank(message = "Email is required")
        @Email(message = "Incorrect email format")
        @Size(max = 128, message = "An email cannot exceed 128 characters.")
        String email,

        @NotBlank(message = "The role is required")
        @Size(max = 32, message = "The role cannot exceed 32 characters.")
        String role,

        Long groupId
) {
}
