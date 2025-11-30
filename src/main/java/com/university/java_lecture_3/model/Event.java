package com.university.java_lecture_3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    public static final int NO_LIMIT_TO_CAPACITY = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length =  256)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "event_time", nullable = false)
    private LocalDateTime eventTime;

    @Column(name = "location", nullable = false, length = 256)
    private String location;

    @Column(name = "max_participants")
    private int maxParticipants;

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<EventRegistration> registrations;

    public Event(String title, String description, LocalDateTime eventTime, String location, int maxParticipants) {
        this.title = title;
        this.description = description;
        this.eventTime = eventTime;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.registrations = new ArrayList<>();
    }

    public Event(String title, String description, LocalDateTime eventTime, String location) {
        this(title, description, eventTime, location, NO_LIMIT_TO_CAPACITY);
    }

}
