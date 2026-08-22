package com.linksphere.user.dto;

public class FollowUserResponse {

    private Long id;
    private String username;
    private String fullName;
    private String profilePicture;

    public FollowUserResponse() {
    }

    public FollowUserResponse(Long id,
                              String username,
                              String fullName,
                              String profilePicture) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.profilePicture = profilePicture;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}