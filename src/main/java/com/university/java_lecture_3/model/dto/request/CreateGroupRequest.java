package com.university.java_lecture_3.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateGroupRequest {
    private String name;
    private String curatorName;
}
