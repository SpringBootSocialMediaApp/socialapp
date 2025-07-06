// src/main/java/com/example/socialapp/service/UserService.java
package com.example.socialapp.service;

import com.example.socialapp.dto.UserRegistrationDto;
import com.example.socialapp.model.User;

public interface UserService {
    /** Returns true if an account with this email already exists */
    boolean emailExists(String email);

    /** Register a new user (hashes password, saves, returns the saved User) */
    User registerNewUser(UserRegistrationDto registrationDto);

    /** Lookup a user by email, or throw if not found */
    User findByEmail(String email);
}
