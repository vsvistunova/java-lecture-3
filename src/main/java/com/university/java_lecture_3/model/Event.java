package com.university.java_lecture_3.model;

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
    private String title;
    private String description;
    private LocalDateTime eventTime;
    private String location;
    private Integer maxRegistrations;
    private List<User> users;
}
