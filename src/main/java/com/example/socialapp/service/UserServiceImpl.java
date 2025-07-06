// src/main/java/com/example/socialapp/service/UserServiceImpl.java
package com.example.socialapp.service;

import com.example.socialapp.dto.UserRegistrationDto;
import com.example.socialapp.model.User;
import com.example.socialapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean emailExists(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    @Transactional
    public User registerNewUser(UserRegistrationDto dto) {
        // Build the User entity
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();

        // Save and return
        return userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found: " + email));
    }
}
