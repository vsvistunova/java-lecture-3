package com.d1sxrder3d.java_spring_prfct.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events = new ArrayList<>();

    public User() {}
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}