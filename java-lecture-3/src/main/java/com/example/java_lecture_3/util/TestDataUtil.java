package com.example.java_lecture_3.util;

import com.example.java_lecture_3.model.Event;
import com.example.java_lecture_3.model.User;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {
    // Метод для создания тестовых данных
    public static List<User> createTestUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Анна", 25, "anna@mail.com"));
        users.add(new User(2L, "Иван", 30, "ivan@mail.com"));
        users.add(new User(3L, "Мария", 22, "maria@mail.com"));
        users.add(new User(4L, "Петр", 35, "petr@mail.com"));
        users.add(new User(5L, "Ольга", 28, "olga@mail.com"));
        users.add(new User(6L, "Сергей", 27, "sergey@mail.com"));
        users.add(new User(7L, "Елена", 29, "elena@mail.com"));
        users.add(new User(8L, "Алексей", 31, "alex@mail.com"));
        users.add(new User(9L, "Дмитрий", 26, "dmitry@mail.com"));
        users.add(new User(10L, "Светлана", 33, "svetlana@mail.com"));
        return users;
    }
    public  static List<Event> createTestEvents() {
        List<Event> events = new ArrayList<>();
        return events;
    }
}