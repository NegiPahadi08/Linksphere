package com.linksphere.user.service;

import com.linksphere.user.entity.Follow;
import com.linksphere.user.entity.User;
import com.linksphere.user.repository.FollowRepository;
import com.linksphere.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository,
                         UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    // Follow another user
    public String followUser(String email, Long userId) {

        User follower = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        User following = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Target user not found."));

        if (follower.getId().equals(following.getId())) {
            throw new RuntimeException("You cannot follow yourself.");
        }

        if (followRepository.findByFollowerAndFollowing(follower, following).isPresent()) {
            throw new RuntimeException("Already following this user.");
        }

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);

        return "You are now following " + following.getUsername();
    }
}