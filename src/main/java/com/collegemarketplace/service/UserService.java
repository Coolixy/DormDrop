package com.collegemarketplace.service;

import com.collegemarketplace.entity.User;
import com.collegemarketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(String email, String name, Date dob, String password) {
        // Validate college email
        if (!email.endsWith("@bennett.edu.in")) {
            throw new IllegalArgumentException("Invalid college email ID");
        }

        // Check if user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(password);

        // Create and save user
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setDob(dob);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }
}