package com.AdaSigorta.controller;

import com.AdaSigorta.dto.LoginRequest;
import com.AdaSigorta.entity.RegisterRequest;
import com.AdaSigorta.entity.User;
import com.AdaSigorta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // React uygulamanızın portunu ekleyin

public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (authenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail()
        );
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/session-info")
    public ResponseEntity<?> getSessionInfo(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("User is logged in as: " + authentication.getName());
        } else {
            return ResponseEntity.ok("User is not logged in");
        }
    }
}
