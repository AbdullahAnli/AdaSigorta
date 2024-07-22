package com.AdaSigorta.controller;

import com.AdaSigorta.entity.RegisterRequest;
import com.AdaSigorta.entity.User;
import com.AdaSigorta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

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
