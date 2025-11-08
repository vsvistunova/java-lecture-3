package com.university.java_lecture_3.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Класс User с использованием Lombok
@Data // автоматически создает геттеры, сеттеры, equals, hashCode, toString
@NoArgsConstructor // создает пустой конструктор
@AllArgsConstructor // создает конструктор со всеми полями
public class User {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
}