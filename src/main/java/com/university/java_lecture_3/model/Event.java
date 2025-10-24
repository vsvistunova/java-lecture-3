package com.university.java_lecture_3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Event {

    public static final int NO_LIMIT_TO_CAPACITY = 0;

    private Long id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private int maxNumOfUsers;
    private List<User> signedUpUsers;

    public Event(String name, String description, LocalDateTime dateTime, String location, int maxNumOfUsers) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
        this.maxNumOfUsers = maxNumOfUsers;
        this.signedUpUsers = new ArrayList<>();
    }

    public Event(String name, String description, LocalDateTime dateTime, String location) {
        this(name, description, dateTime, location, NO_LIMIT_TO_CAPACITY);
    }

    public void registerUser(User user) {
        signedUpUsers.add(user);
    }

    public boolean hasFreePlaces() {
        return maxNumOfUsers != NO_LIMIT_TO_CAPACITY && signedUpUsers.size() >= maxNumOfUsers;
    }

}
