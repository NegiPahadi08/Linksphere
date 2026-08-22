package com.linksphere.user.controller;

import com.linksphere.user.dto.LikeUserResponse;
import com.linksphere.user.service.LikeService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Like Post
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(
            Authentication authentication,
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                likeService.likePost(
                        authentication.getName(),
                        postId
                )
        );
    }

    // Unlike Post
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> unlikePost(
            Authentication authentication,
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                likeService.unlikePost(
                        authentication.getName(),
                        postId
                )
        );
    }

    // Get Like Count
    @GetMapping("/{postId}/likes/count")
    public ResponseEntity<Long> getLikeCount(
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                likeService.getLikeCount(postId)
        );
    }

    // Get Users Who Liked Post
    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<LikeUserResponse>> getLikedUsers(
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                likeService.getLikedUsers(postId)
        );
    }
}