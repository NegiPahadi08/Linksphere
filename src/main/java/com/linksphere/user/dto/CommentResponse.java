package com.linksphere.user.dto;

import java.time.LocalDateTime;

public class CommentResponse {

    private Long id;
    private Long postId;
    private Long userId;
    private String username;
    private String fullName;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse() {
    }

    public CommentResponse(
            Long id,
            Long postId,
            Long userId,
            String username,
            String fullName,
            String content,
            LocalDateTime createdAt) {

        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
