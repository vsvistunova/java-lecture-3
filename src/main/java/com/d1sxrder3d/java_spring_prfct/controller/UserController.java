package com.d1sxrder3d.java_spring_prfct.controller;

import com.d1sxrder3d.java_spring_prfct.model.User;
import com.d1sxrder3d.java_spring_prfct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> GetById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User updatedUser) {
        User oldUser = userRepository.findById(id).get();
        oldUser.setName(updatedUser.getName());
        oldUser.setEmail(updatedUser.getEmail());

        userRepository.save(oldUser);

        return oldUser;

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}