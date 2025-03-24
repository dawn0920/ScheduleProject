package org.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleCheckRequestDto {
    private String name;
    private LocalDate date_correction;
}
