package com.university.java_lecture_3.util;

import com.university.java_lecture_3.model.Event;
import com.university.java_lecture_3.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

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

    public static List<Event> createTestEvents() {
        List<Event> events = new ArrayList<>();
        List<User> users1 = new ArrayList<>();
        users1.add(new User(1L, "Анна", 25, "anna@mail.com"));
        users1.add(new User(2L, "Иван", 30, "ivan@mail.com"));
        users1.add(new User(3L, "Мария", 22, "maria@mail.com"));
        events.add(new Event(
                1L,
                "Название 1",
                "Описание 1",
                LocalDateTime.of(2000, 1, 1, 0, 0, 0, 0),
                "Место 1",
                3,
                users1));
        List<User> users2 = new ArrayList<>();
        users2.add(new User(4L, "Петр", 35, "petr@mail.com"));
        users2.add(new User(5L, "Ольга", 28, "olga@mail.com"));
        users2.add(new User(6L, "Сергей", 27, "sergey@mail.com"));
        events.add(new Event(
                2L,
                "Название 2",
                "Описание 2",
                LocalDateTime.of(2010, 2, 2, 12, 0, 0, 0),
                "Место 2",
                30,
                users2));
        List<User> users3 = new ArrayList<>();
        users3.add(new User(7L, "Елена", 29, "elena@mail.com"));
        users3.add(new User(8L, "Алексей", 31, "alex@mail.com"));
        events.add(new Event(
                3L,
                "Название 3",
                "Описание 3",
                LocalDateTime.of(2020, 4, 3, 9, 30, 0, 0),
                "Место 3",
                50,
                users3));
        List<User> users4 = new ArrayList<>();
        users4.add(new User(9L, "Дмитрий", 26, "dmitry@mail.com"));
        users4.add(new User(10L, "Светлана", 33, "svetlana@mail.com"));
        events.add(new Event(
                4L,
                "Название 4",
                "Описание 4",
                LocalDateTime.of(2030, 9, 2, 10, 0, 0, 0),
                "Место 4",
                3,
                users4));
        return events;
    }
}
