package com.omnia.Involutio.service;

import com.omnia.Involutio.entity.UserEntity;
import com.omnia.Involutio.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByLogin(username).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
