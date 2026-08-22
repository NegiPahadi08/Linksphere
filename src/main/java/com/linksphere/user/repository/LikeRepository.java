package com.linksphere.user.repository;

import com.linksphere.user.entity.Like;
import com.linksphere.user.entity.Post;
import com.linksphere.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);

    long countByPost(Post post);
}