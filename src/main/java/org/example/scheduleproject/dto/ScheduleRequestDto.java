package org.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {
    private String schedule;
    private String name;
    private String password;
    private LocalDate date_post; //생성 시 자동 설정
}
