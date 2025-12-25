package com.university.java_lecture_3.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequest {
    private String name;
    private Integer age;
    private Long groupId;
}
