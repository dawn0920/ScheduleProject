package org.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserRequestDto {
    private String name;
    private String email;
    private LocalDate date_post;
}
