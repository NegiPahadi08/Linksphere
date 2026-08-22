package com.linksphere.user.dto;

import java.time.LocalDateTime;

public class PostResponse {

    private Long id;
    private String content;

    private Long authorId;
    private String authorUsername;
    private String authorFullName;
    private String authorProfilePicture;

    private LocalDateTime createdAt;

    private long likesCount;
    private boolean likedByCurrentUser;

    public PostResponse() {
    }

    public PostResponse(Long id,
                        String content,
                        Long authorId,
                        String authorUsername,
                        String authorFullName,
                        String authorProfilePicture,
                        LocalDateTime createdAt,
                        long likesCount,
                        boolean likedByCurrentUser) {

        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.authorUsername = authorUsername;
        this.authorFullName = authorFullName;
        this.authorProfilePicture = authorProfilePicture;
        this.createdAt = createdAt;
        this.likesCount = likesCount;
        this.likedByCurrentUser = likedByCurrentUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public String getAuthorProfilePicture() {
        return authorProfilePicture;
    }

    public void setAuthorProfilePicture(String authorProfilePicture) {
        this.authorProfilePicture = authorProfilePicture;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isLikedByCurrentUser() {
        return likedByCurrentUser;
    }

    public void setLikedByCurrentUser(boolean likedByCurrentUser) {
        this.likedByCurrentUser = likedByCurrentUser;
    }
}