package com.university.java_lecture_3.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private String curatorName;
    private int userCount;

}
