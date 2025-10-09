package com.d1sxrder3d.java_spring_prfct.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime eventDateTime;

    @Column(nullable = false)
    private String location;

    private Integer maxParticipants;

    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants = new ArrayList<>();

    public Event() {}

    public Event(String name, String description, LocalDateTime eventDateTime, String location, Integer maxParticipants) {
        this.name = name;
        this.description = description;
        this.eventDateTime = eventDateTime;
        this.location = location;
        this.maxParticipants = maxParticipants;
    }

    public boolean isFull() {
        return maxParticipants != null && participants.size() >= maxParticipants;
    }

    public boolean isUpcoming() {
        return eventDateTime.isAfter(LocalDateTime.now());
    }

    public boolean isUserRegistered(User user) {
        return participants.contains(user);
    }
}