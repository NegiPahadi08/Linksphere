package com.linksphere.user.service;

import com.linksphere.user.dto.LikeUserResponse;
import com.linksphere.user.entity.Like;
import com.linksphere.user.entity.Post;
import com.linksphere.user.entity.User;
import com.linksphere.user.repository.LikeRepository;
import com.linksphere.user.repository.PostRepository;
import com.linksphere.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository,
                       PostRepository postRepository,
                       UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // Like Post
    public String likePost(String email, Long postId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found."));

        if (likeRepository.findByUserAndPost(user, post).isPresent()) {
            throw new RuntimeException("You already liked this post.");
        }

        Like like = new Like();
        like.setUser(user);
        like.setPost(post);

        likeRepository.save(like);

        return "Post liked successfully.";
    }

    // Unlike Post
    public String unlikePost(String email, Long postId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found."));

        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new RuntimeException("You have not liked this post."));

        likeRepository.delete(like);

        return "Post unliked successfully.";
    }

    // Get Like Count
    public long getLikeCount(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found."));

        return likeRepository.countByPost(post);
    }

    // Get Users Who Liked the Post
    public List<LikeUserResponse> getLikedUsers(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found."));

        return likeRepository.findByPost(post)
                .stream()
                .map(Like::getUser)
                .map(user -> new LikeUserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getProfilePicture()
                ))
                .collect(Collectors.toList());
    }
}