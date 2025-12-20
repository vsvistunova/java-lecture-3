package com.university.java_lecture_3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "curator_name", nullable = false, length = 128)
    private String curatorName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

}
