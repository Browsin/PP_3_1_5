package ru.kata.spring_js_restControllers.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring_js_restControllers.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    List<User> getAllUsers();

    User findById(Long id);

    void createOrUpdateUser(User user);

    void deleteUser(Long id);
}
