package com.university.java_lecture_3.service;

import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.IdGeneratorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
        createTestUsers();
    }

    public Optional<User> findById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public List<User> findAllByAge(int minAge, int maxAge) {
        List<User> result = new ArrayList<>();

        for (User user : users) {
            if (user.getAge() >= minAge && user.getAge() <= maxAge) {
                result.add(user);
            }
        }

        return result;
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(IdGeneratorUtil.generate(User.class));
        users.add(user);

        return user;
    }

    public User update(Long id, User user) {
        User updatedUser = findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
        updatedUser.setName(user.getName());
        updatedUser.setAge(user.getAge());
        updatedUser.setEmail(user.getEmail());

        return updatedUser;
    }

    public boolean delete(Long id) {
        if (findById(id).isPresent()) {
            users.remove(findById(id).get());
            return true;
        }

        return false;
    }

    private void createTestUsers() {
        save(new User("Анна", 25, "anna@mail.com"));
        save(new User("Иван", 30, "ivan@mail.com"));
        save(new User("Мария", 22, "maria@mail.com"));
        save(new User("Петр", 35, "petr@mail.com"));
        save(new User("Ольга", 28, "olga@mail.com"));
        save(new User("Сергей", 27, "sergey@mail.com"));
        save(new User("Елена", 29, "elena@mail.com"));
        save(new User("Алексей", 31, "alex@mail.com"));
        save(new User("Дмитрий", 26, "dmitry@mail.com"));
        save(new User("Светлана", 33, "svetlana@mail.com"));
    }

}
