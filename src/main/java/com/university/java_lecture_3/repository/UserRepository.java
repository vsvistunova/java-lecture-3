package com.university.java_lecture_3.repository;

import com.university.java_lecture_3.exception.UserValidationException;
import com.university.java_lecture_3.model.User;
import com.university.java_lecture_3.util.IdGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final List<User> users;

    public List<User> findAllByAge(int minAge, int maxAge) {
        return users.stream()
                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
                .toList();
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        users.add(user);
        user.setId(IdGeneratorUtil.generate(User.class));

        return user;
    }

    public User update(Long id, User user) {
        User updatedUser = findById(id).orElseThrow(
                () -> new UserValidationException("User with id " + id + " not found")
        );

        updatedUser.setName(user.getName());
        updatedUser.setAge(user.getAge());
        updatedUser.setEmail(user.getEmail());

        return updatedUser;
    }

    public boolean delete(Long id) {
        Optional<User> optionalUser = findById(id);

        if (optionalUser.isPresent()) {
            users.remove(optionalUser.get());
            return true;
        }

        return false;
    }

}
