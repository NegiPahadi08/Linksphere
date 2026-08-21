package com.linksphere.user.controller;

import com.linksphere.user.dto.UpdateProfileRequest;
import com.linksphere.user.dto.UserProfileResponse;
import com.linksphere.user.entity.User;
import com.linksphere.user.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(service.createUser(user));
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return service.getUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get Current Logged-in User
    @GetMapping("/me")
    public UserProfileResponse currentUser(Authentication authentication) {
        return service.getCurrentUser(authentication.getName());
    }

    // Update Current User Profile
    @PutMapping("/profile")
    public UserProfileResponse updateProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request) {

        return service.updateProfile(authentication.getName(), request);
    }
}