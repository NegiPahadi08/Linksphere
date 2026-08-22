package com.linksphere.user.controller;

import com.linksphere.user.dto.FollowUserResponse;
import com.linksphere.user.dto.UpdateProfileRequest;
import com.linksphere.user.dto.UserProfileResponse;
import com.linksphere.user.entity.User;
import com.linksphere.user.service.FollowService;
import com.linksphere.user.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final FollowService followService;

    public UserController(UserService service, FollowService followService) {
        this.service = service;
        this.followService = followService;
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

    // Upload Profile Picture
    @PostMapping("/profile-picture")
    public UserProfileResponse uploadProfilePicture(
            Authentication authentication,
            @RequestParam("file") MultipartFile file) throws Exception {

        return service.uploadProfilePicture(authentication.getName(), file);
    }

    // Follow a User
    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> followUser(
            Authentication authentication,
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                followService.followUser(authentication.getName(), userId)
        );
    }

    // Unfollow a User
    @DeleteMapping("/unfollow/{userId}")
    public ResponseEntity<String> unfollowUser(
            Authentication authentication,
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                followService.unfollowUser(authentication.getName(), userId)
        );
    }

    // Get Followers of a User
    @GetMapping("/{id}/followers")
    public ResponseEntity<List<FollowUserResponse>> getFollowers(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                followService.getFollowers(id)
        );
    }

    // Get Users That a User is Following
    @GetMapping("/{id}/following")
    public ResponseEntity<List<FollowUserResponse>> getFollowing(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                followService.getFollowing(id)
        );
    }
}