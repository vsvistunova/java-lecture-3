package com.university.java_lecture_3.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailedResponse {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String role;
    private GroupResponse group;
    private List<EventResponse> events;
}
