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

    public PostResponse() {
    }

    public PostResponse(Long id,
                        String content,
                        Long authorId,
                        String authorUsername,
                        String authorFullName,
                        String authorProfilePicture,
                        LocalDateTime createdAt) {

        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.authorUsername = authorUsername;
        this.authorFullName = authorFullName;
        this.authorProfilePicture = authorProfilePicture;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public String getAuthorProfilePicture() {
        return authorProfilePicture;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}