package com.university.java_lecture_3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email", unique = true, nullable = false, length = 128)
    private String email;

    @Column(name = "role", nullable = false, length = 32)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<EventRegistration> eventRegistrations;

}