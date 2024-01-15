package ru.kata.spring_bootstrap_demo.service;

import ru.kata.spring_bootstrap_demo.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    void saveRole(Role role);

    List<Role> findRole(String roleUser);
}