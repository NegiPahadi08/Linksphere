package com.linksphere.user.controller;

import com.linksphere.user.dto.CommentResponse;
import com.linksphere.user.dto.CreateCommentRequest;
import com.linksphere.user.service.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Create Comment
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            Authentication authentication,
            @PathVariable Long postId,
            @RequestBody CreateCommentRequest request) {

        return ResponseEntity.ok(
                commentService.createComment(
                        authentication.getName(),
                        postId,
                        request
                )
        );
    }

    // Get Comments
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                commentService.getComments(postId)
        );
    }

    // Delete Comment
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            Authentication authentication,
            @PathVariable Long commentId) {

        return ResponseEntity.ok(
                commentService.deleteComment(
                        authentication.getName(),
                        commentId
                )
        );
    }
}