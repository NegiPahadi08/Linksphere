package com.linksphere.user.service;

import com.linksphere.user.dto.UpdateProfileRequest;
import com.linksphere.user.dto.UserProfileResponse;
import com.linksphere.user.entity.User;
import com.linksphere.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Create User
    public User createUser(User user) {
        return repository.save(user);
    }

    // Get User by ID
    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    // Get Current Logged-in User
    public UserProfileResponse getCurrentUser(String email) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getBio(),
                user.getProfilePicture(),
                user.getCreatedAt()
        );
    }

    // Update Current User Profile
    public UserProfileResponse updateProfile(String email, UpdateProfileRequest request) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setFullName(request.getFullName());
        user.setBio(request.getBio());

        User updatedUser = repository.save(user);

        return new UserProfileResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getEmail(),
                updatedUser.getFullName(),
                updatedUser.getBio(),
                updatedUser.getProfilePicture(),
                updatedUser.getCreatedAt()
        );
    }
}