package org.example.scheduleproject.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class UserRequestDto {
    private String name;

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    private LocalDate date_post;
}
