package ru.kata.spring_bootstrap_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring_bootstrap_demo.model.User;
import ru.kata.spring_bootstrap_demo.service.RoleService;
import ru.kata.spring_bootstrap_demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPanel(Model model, @ModelAttribute("user") User user,
                             Principal principal) {
        User authUser = userService.findByUsername(principal.getName());
        model.addAttribute("authenticatedUser", authUser);
        model.addAttribute("rolesAuthenticatedUser", authUser.getRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRole());
        return "/adminProfile";
    }

    @PostMapping("/createOrUpdateUser")
    public String createOrUpdateUser(@ModelAttribute("user") User user) {
        userService.createOrUpdateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
