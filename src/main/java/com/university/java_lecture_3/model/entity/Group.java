package com.university.java_lecture_3.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@Getter
@Setter

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",  nullable = false, length = 50)
    private String name;

    @Column(name = "curator_name",nullable = false, length = 100)
    private String curatorName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users =  new ArrayList<>();
}
