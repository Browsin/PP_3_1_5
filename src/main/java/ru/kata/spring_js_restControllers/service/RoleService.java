package ru.kata.spring_js_restControllers.service;

import ru.kata.spring_js_restControllers.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    void saveRole(Role role);

    List<Role> findRole(String roleUser);
}