package com.university.java_lecture_3.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GroupRequest(
        @NotBlank(message = "The group name is required")
        @Size(min = 2, max = 64, message = "The group name must contain from 2 to 64 characters.")
        String name,

        @NotBlank(message = "The curator's name is required")
        @Size(min = 2, max = 128, message = "The curator's name must contain from 2 to 128 characters.")
        String curatorName
) {
}
