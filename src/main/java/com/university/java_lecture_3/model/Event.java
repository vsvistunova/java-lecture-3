package com.university.java_lecture_3.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Event {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private int maxNumOfPeople;
    private List<User> signedUpUsers;

    public Event(String name, String description, LocalDateTime dateTime, String location, int maxNumOfPeople) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.maxNumOfPeople = maxNumOfPeople;
        this.signedUpUsers = new ArrayList<>();
    }

    public Event(String name, String description, LocalDateTime dateTime, String location) {
        this(name, description, dateTime, location, 0);
    }

}
