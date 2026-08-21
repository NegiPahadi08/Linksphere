package com.linksphere.user.service;

import com.linksphere.user.dto.UpdateProfileRequest;
import com.linksphere.user.dto.UserProfileResponse;
import com.linksphere.user.entity.User;
import com.linksphere.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir;

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

    // Upload Profile Picture
    public UserProfileResponse uploadProfilePicture(String email, MultipartFile file)
            throws IOException {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        // Create upload folder if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate unique filename
        String fileName = user.getUsername() + "-" +
                System.currentTimeMillis() + "-" +
                file.getOriginalFilename();

        Path filePath = uploadPath.resolve(fileName);

        // Save image
        Files.copy(file.getInputStream(), filePath,
                StandardCopyOption.REPLACE_EXISTING);

        // Save image URL in database
        user.setProfilePicture("/uploads/" + fileName);

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