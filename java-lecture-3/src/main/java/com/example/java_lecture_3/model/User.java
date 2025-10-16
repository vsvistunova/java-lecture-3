package com.example.java_lecture_3.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Класс User с использованием Lombok
@Data // автоматически создает геттеры, сеттеры, equals, hashCode, toString
@NoArgsConstructor // создает пустой конструктор
@AllArgsConstructor // создает конструктор со всеми полями
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
}