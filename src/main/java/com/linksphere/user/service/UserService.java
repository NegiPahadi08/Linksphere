package com.linksphere.user.service;

import com.linksphere.user.entity.User;
import com.linksphere.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }
}