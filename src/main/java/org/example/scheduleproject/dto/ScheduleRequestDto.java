package org.example.scheduleproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ScheduleRequestDto {
    @NotBlank(message = "할일은 필수 입력 항목입니다.")
    @Size(max = 200, message = "할일은 최대 200자까지 입력할 수 있습니다.")
    private String schedule;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password;

    private String name;
    private LocalDate date_post; //생성 시 자동 설정
}
