package com.linksphere.user.service;

import com.linksphere.user.dto.FollowUserResponse;
import com.linksphere.user.entity.Follow;
import com.linksphere.user.entity.User;
import com.linksphere.user.repository.FollowRepository;
import com.linksphere.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // Unfollow a user
    public String unfollowUser(String email, Long userId) {

        User follower = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        User following = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Target user not found."));

        Follow follow = followRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(() -> new RuntimeException("You are not following this user."));

        followRepository.delete(follow);

        return "You unfollowed " + following.getUsername();
    }

    // Get followers of a user
    public List<FollowUserResponse> getFollowers(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return followRepository.findByFollowing(user)
                .stream()
                .map(follow -> {
                    User follower = follow.getFollower();

                    return new FollowUserResponse(
                            follower.getId(),
                            follower.getUsername(),
                            follower.getFullName(),
                            follower.getProfilePicture()
                    );
                })
                .collect(Collectors.toList());
    }

    // Get users that a user is following
    public List<FollowUserResponse> getFollowing(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return followRepository.findByFollower(user)
                .stream()
                .map(follow -> {
                    User following = follow.getFollowing();

                    return new FollowUserResponse(
                            following.getId(),
                            following.getUsername(),
                            following.getFullName(),
                            following.getProfilePicture()
                    );
                })
                .collect(Collectors.toList());
    }
}