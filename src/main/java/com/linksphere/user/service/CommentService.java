package com.linksphere.user.service;

import com.linksphere.user.dto.CommentResponse;
import com.linksphere.user.dto.CreateCommentRequest;
import com.linksphere.user.entity.Comment;
import com.linksphere.user.entity.Post;
import com.linksphere.user.entity.User;
import com.linksphere.user.repository.CommentRepository;
import com.linksphere.user.repository.PostRepository;
import com.linksphere.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // Create Comment
    public CommentResponse createComment(
            String email,
            Long postId,
            CreateCommentRequest request) {

        if (request.getContent() == null ||
                request.getContent().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Comment content cannot be empty."
            );
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found.")
                );

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new RuntimeException("Post not found.")
                );

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(request.getContent().trim());

        Comment saved = commentRepository.save(comment);

        return toResponse(saved);
    }

    // Get Comments for a Post
    public List<CommentResponse> getComments(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found.");
        }

        return commentRepository
                .findByPostIdOrderByCreatedAtAsc(postId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Delete Comment
    public String deleteComment(
            String email,
            Long commentId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found.")
                );

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new RuntimeException("Comment not found.")
                );

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException(
                    "You can only delete your own comment."
            );
        }

        commentRepository.delete(comment);

        return "Comment deleted successfully.";
    }

    private CommentResponse toResponse(Comment comment) {

        User user = comment.getUser();

        return new CommentResponse(
                comment.getId(),
                comment.getPost().getId(),
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
