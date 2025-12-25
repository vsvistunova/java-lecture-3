package com.university.java_lecture_3.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSummaryResponse {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String role;
    private String groupName;
    private int eventCount;
}
