package com.university.java_lecture_3.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequest {
    private String name;

    private Integer age;

    private String email;

    private String role;

    private Long groupId;
}
