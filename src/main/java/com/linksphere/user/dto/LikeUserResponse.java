package com.linksphere.user.dto;

public class LikeUserResponse {

    private Long id;
    private String username;
    private String fullName;
    private String profilePicture;

    public LikeUserResponse() {
    }

    public LikeUserResponse(Long id,
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}