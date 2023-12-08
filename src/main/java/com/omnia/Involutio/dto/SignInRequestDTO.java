package com.omnia.Involutio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignInRequestDTO {
    public String login;
    public String password;

    public SignInRequestDTO() {
    }

    public SignInRequestDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
