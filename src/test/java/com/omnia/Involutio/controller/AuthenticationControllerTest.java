package com.omnia.Involutio.controller;

import com.omnia.Involutio.entity.ERole;
import com.omnia.Involutio.entity.UserEntity;
import com.omnia.Involutio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class AuthenticationControllerTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthenticationController authenticationController;

    @Test
    void authenticationToken() {
    }

    @Test
    void authentication() {
        UserEntity user = new UserEntity("bublic", "buba", "1234", ERole.MANAGER);


    }
}