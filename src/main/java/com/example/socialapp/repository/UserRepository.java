package com.example.socialapp.repository;

import com.example.socialapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /** Lookup a user by email */
    Optional<User> findByEmail(String email);
}
