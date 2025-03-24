package org.example.scheduleproject.dto;

import lombok.Getter;
import org.example.scheduleproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private int schedule_id;
    private String schedule;
    private String name;
    private String password;
    private LocalDateTime date_post;
    private LocalDateTime date_correction;

    // Schedule class를 인자로 가지는 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.schedule_id = getSchedule_id();
        this.schedule = getSchedule();
        this.name = getName();
        this.password = getPassword();
        this.date_post = getDate_post();
        this.date_correction = getDate_correction();
    }
}
