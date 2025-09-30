package com.collegemarketplace.controller;

import com.collegemarketplace.entity.User;
import com.collegemarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestParam String email,
                       @RequestParam String name,
                       @RequestParam String dob,
                       @RequestParam String password) {
        return userService.registerUser(email, name, new Date(dob), password);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {
        return userService.loginUser(email, password);
    }
}