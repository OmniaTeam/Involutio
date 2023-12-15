package com.omnia.Involutio.security.config;

import com.omnia.Involutio.entity.ERole;
import com.omnia.Involutio.entity.UserEntity;
import com.omnia.Involutio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Data {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public void createUser() {
        if (!userRepository.findAll().isEmpty()) return;
        UserEntity userAdmin = new UserEntity("Bublic", "buba", passwordEncoder.encode("1234"), ERole.MANAGER);
        userRepository.save(userAdmin);
    }
}
