package com.omnia.Involutio.controller;

import com.omnia.Involutio.entity.ERole;
import com.omnia.Involutio.entity.UserEntity;
import com.omnia.Involutio.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class AuthenticationControllerIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void testData() {
        if (!userRepository.findAll().isEmpty()) return;
        UserEntity user = new UserEntity("Bublic", "buba", passwordEncoder.encode("1234"), ERole.MANAGER);
        userRepository.save(user);
    }

    @Test
    void authentication_IsValidUser_ReturnsValidResponseEntity() throws Exception {
        // given

        var requestBuilder = post("/api/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
{
    "login": "buba",
    "password": "1234"
}
""");
        // when
        this.mockMvc.perform(requestBuilder)
        // then
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(
                    """
                    {
                        "fio": "Bublic",
                        "login": "buba",
                        "classification": "MANAGER"
                    }
                    """),
                        jsonPath("$.id").exists(),
                        cookie().exists("access_token")


                );
    }

    @Test
    void authentication_NotExistUser_Returns404StatusCode() throws Exception {
        // given

        var requestBuilder = post("/api/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
{
    "login": "notexist",
    "password": "1234"
}
""");
        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andExpectAll(
                        status().is(404)
                );
    }

    @Test
    void authentication_IncorrectPassword_Returns403StatusCode() throws Exception {
        // given

        var requestBuilder = post("/api/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
{
    "login": "buba",
    "password": "12345"
}
""");
        // when
        this.mockMvc.perform(requestBuilder)
                // then
                .andExpectAll(
                        status().is(403)
                );
    }

}
