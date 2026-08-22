package com.linksphere.user.controller;

import com.linksphere.user.dto.CreatePostRequest;
import com.linksphere.user.dto.PostResponse;
import com.linksphere.user.dto.UpdatePostRequest;
import com.linksphere.user.service.PostService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create Post
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            Authentication authentication,
            @RequestBody CreatePostRequest request) {

        return ResponseEntity.ok(
                postService.createPost(
                        authentication.getName(),
                        request
                )
        );
    }

    // Get Post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(
            Authentication authentication,
            @PathVariable Long id) {

        return ResponseEntity.ok(
                postService.getPost(
                        id,
                        authentication.getName()
                )
        );
    }

    // Get all Posts of a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUser(
            Authentication authentication,
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                postService.getPostsByUser(
                        userId,
                        authentication.getName()
                )
        );
    }

    // Get Feed with Pagination
    @GetMapping("/feed")
    public ResponseEntity<List<PostResponse>> getFeed(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                postService.getFeed(
                        authentication.getName(),
                        page,
                        size
                )
        );
    }

    // Update Post
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody UpdatePostRequest request) {

        return ResponseEntity.ok(
                postService.updatePost(
                        authentication.getName(),
                        id,
                        request
                )
        );
    }
}