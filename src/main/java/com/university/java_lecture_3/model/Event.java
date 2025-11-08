package com.university.java_lecture_3.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "DateTime is mandatory")
    private LocalDateTime dateTime;

    @NotBlank(message = "Location is mandatory")
    private String location;
    @Min(value = 0, message = "Capacity must be non-negative")
    private Integer capacity;

    private List<User> attendees;
}