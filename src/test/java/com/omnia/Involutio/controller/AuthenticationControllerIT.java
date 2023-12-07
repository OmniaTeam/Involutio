package com.omnia.Involutio.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/users/test_data.sql")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class AuthenticationControllerIT {
    @Autowired
    MockMvc mockMvc;

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
                        content().json("""
                    {
                        "id": 1,
                        "fio": "Bublic",
                        "login": "buba",
                        "classification": "MANAGER"
                    }
""")
                );
    }

}
