package org.example.scheduleproject.dto;

import lombok.Getter;

@Getter
public class PasswordRequestDto {
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
