package ru.kata.spring_bootstrap_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring_bootstrap_demo.model.User;
import ru.kata.spring_bootstrap_demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userInfo(Model model, Principal principal)  {
        User authUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", authUser);
        model.addAttribute("userRoles", authUser.getRoles());
        return "userProfile";
    }
}
