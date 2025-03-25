package org.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponseDto {
    private int id;
    private String name;
    private String email;
    private LocalDate date_post;
    private LocalDate date_correction;

    public UserResponseDto(int id, String name, String email, LocalDate date_post, LocalDate date_correction) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.date_post = date_post;
        this.date_correction = date_correction;
    }
}
