package com.linksphere.user.dto;

public class AuthResponse {

    private Long id;
    private String username;
    private String email;
    private String token;
    private String message;

    public AuthResponse() {}

    // Register/Login without token
    public AuthResponse(Long id, String username, String email, String message) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.message = message;
    }

    // Login with JWT token
    public AuthResponse(Long id, String username, String email, String token, String message) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;
        this.message = message;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}