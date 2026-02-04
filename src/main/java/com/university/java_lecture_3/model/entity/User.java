package com.university.java_lecture_3.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Класс User с использованием Lombok
@Entity
@Table(name = "users")
@Data // автоматически создает геттеры, сеттеры, equals, hashCode, toString
@NoArgsConstructor // создает пустой конструктор
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "email", nullable = false, length = 150)
  private String email;

  @Column(name = "role", nullable = false, length = 20)
  private String role;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

  @ManyToMany()
  @JoinTable(
    name = "event_registrations",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "event_id")
  )
  private Set<Event> registeredEvents =  new HashSet<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<EventRegistration> eventRegistrations = new ArrayList<>();

}