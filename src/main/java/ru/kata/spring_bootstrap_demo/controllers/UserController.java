package ru.kata.spring_bootstrap_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring_bootstrap_demo.model.User;
import ru.kata.spring_bootstrap_demo.service.UserService;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> userInfo(Principal principal)  {
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/")
    public String userInfo(Model model, Principal principal)  {
        model.addAttribute("user",
                userService.findByUsername(principal.getName()));
        return "";
    }

//    @GetMapping()
//    public ResponseEntity<User> getUserByUsername(@AuthenticationPrincipal User user) {
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
}
