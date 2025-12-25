package com.university.java_lecture_3.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;


    @Column(name = "event_time", nullable = false)
    private LocalDateTime eventTime;

    @Column(name = "location", nullable = false, length = 200)
    private String location;

    @Column(name = "max_participants")
    private Integer maxParticipants;

    @ManyToMany(mappedBy = "event_registrations")
    private Set<User> registeredUsers = new HashSet<>();

    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private List<EventRegistration> registrations = new ArrayList<>();

}
