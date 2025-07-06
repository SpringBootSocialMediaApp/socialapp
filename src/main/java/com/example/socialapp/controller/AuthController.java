package com.example.socialapp.controller;

import com.example.socialapp.dto.UserRegistrationDto;
import com.example.socialapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Show the login page. If already authenticated, redirect to home.
     */
    @GetMapping("/login")
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/home";
        }
        return "login";
    }

    /**
     * Display the registration form.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    /**
     * Process registration submissions.
     */
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") @Valid UserRegistrationDto dto,
            BindingResult result,
            Model model
    ) {
        // Check for duplicate email
        if (userService.emailExists(dto.getEmail())) {
            result.rejectValue("email", null, "An account with that email already exists.");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.registerNewUser(dto);
        // After successful registration, redirect to login with a flag
        return "redirect:/login?registered";
    }

    // Logout is handled by Spring Security at /logout
}
