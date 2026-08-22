package com.linksphere.user.service;

import com.linksphere.user.dto.CreatePostRequest;
import com.linksphere.user.dto.PostResponse;
import com.linksphere.user.dto.UpdatePostRequest;
import com.linksphere.user.entity.Post;
import com.linksphere.user.entity.User;
import com.linksphere.user.repository.LikeRepository;
import com.linksphere.user.repository.PostRepository;
import com.linksphere.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       LikeRepository likeRepository) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    // Create Post
    public PostResponse createPost(String email,
                                   CreatePostRequest request) {

        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        if (request.getContent() == null ||
                request.getContent().trim().isEmpty()) {

            throw new RuntimeException("Post content cannot be empty.");
        }

        Post post = new Post();
        post.setContent(request.getContent().trim());
        post.setAuthor(author);

        Post savedPost = postRepository.save(post);

        return toResponse(savedPost, email);
    }

    // Get Post by ID
    public PostResponse getPost(Long postId,
                                String email) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found."));

        return toResponse(post, email);
    }

    // Get all Posts of a User
    public List<PostResponse> getPostsByUser(Long userId,
                                             String email) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return postRepository.findByAuthor(user)
                .stream()
                .map(post -> toResponse(post, email))
                .collect(Collectors.toList());
    }

    // Update Post
    public PostResponse updatePost(String email,
                                   Long postId,
                                   UpdatePostRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found."));

        // Only post owner can update the post
        if (!post.getAuthor().getId().equals(user.getId())) {
            throw new RuntimeException("You can only update your own posts.");
        }

        if (request.getContent() == null ||
                request.getContent().trim().isEmpty()) {

            throw new RuntimeException("Post content cannot be empty.");
        }

        post.setContent(request.getContent().trim());

        Post updatedPost = postRepository.save(post);

        return toResponse(updatedPost, email);
    }

    // Convert Post Entity to Response DTO
    private PostResponse toResponse(Post post,
                                    String email) {

        User author = post.getAuthor();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        long likesCount = likeRepository.countByPost(post);

        boolean likedByCurrentUser =
                likeRepository.findByUserAndPost(currentUser, post).isPresent();

        return new PostResponse(
                post.getId(),
                post.getContent(),
                author.getId(),
                author.getUsername(),
                author.getFullName(),
                author.getProfilePicture(),
                post.getCreatedAt(),
                likesCount,
                likedByCurrentUser
        );
    }
}