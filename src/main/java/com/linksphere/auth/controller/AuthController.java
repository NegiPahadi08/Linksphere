package com.linksphere.auth.controller;

import com.linksphere.auth.entity.User;
import com.linksphere.auth.repository.UserRepository;
import com.linksphere.auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email already registered"));
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return ResponseEntity.ok(
                Map.of("message", "User registered successfully")
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        return userRepository.findByEmail(user.getEmail())
                .map(existingUser -> {

                    if (passwordEncoder.matches(
                            user.getPassword(),
                            existingUser.getPassword())) {

                        String token =
                                jwtService.generateToken(existingUser.getEmail());

                        return ResponseEntity.ok(
                                Map.of(
                                        "message", "Login successful",
                                        "token", token
                                )
                        );
                    }

                    return ResponseEntity.status(401)
                            .body(Map.of(
                                    "message",
                                    "Invalid email or password"
                            ));
                })
                .orElseGet(() ->
                        ResponseEntity.status(401)
                                .body(Map.of(
                                        "message",
                                        "Invalid email or password"
                                ))
                );
    }
}