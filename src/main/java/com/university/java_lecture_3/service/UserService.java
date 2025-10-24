package com.university.java_lecture_3.service;

import com.university.java_lecture_3.exception.UserValidationException;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.repository.UserRepository;
import com.university.java_lecture_3.validation.Validator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Validator<User, UserValidationException> validator;

    @PostConstruct
    public void init() {
        createTestUsers();
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserValidationException("User with id " + id + " not found");
        }

        return optionalUser.get();
    }

    public List<User> findAllByAge(int minAge, int maxAge) {
        return userRepository.findAllByAge(minAge, maxAge);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        validator.validate(user);
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        validator.validate(user);
        return userRepository.update(id, user);
    }

    public boolean delete(Long id) {
        return userRepository.delete(id);
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
