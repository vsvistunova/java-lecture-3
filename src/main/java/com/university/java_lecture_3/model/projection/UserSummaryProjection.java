package com.university.java_lecture_3.model.projection;

public interface UserSummaryProjection {
    Long getId();
    String getName();
    Integer getAge();
    String getEmail();
    String getRole();
    String getGroupName();
    int getEventsCount();
}
