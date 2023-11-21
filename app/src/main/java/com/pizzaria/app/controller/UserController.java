package com.pizzaria.app.controller;

import com.pizzaria.app.entity.UserEntity;
import com.pizzaria.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        String encodedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userService.createUser(user);
    }
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}