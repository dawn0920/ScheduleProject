package org.example.scheduleproject.dto;

import lombok.Getter;
import org.example.scheduleproject.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private int schedule_id;
    private String schedule;
    private String name;
    private String password;
    private LocalDate date_post;
    private LocalDate date_correction;
    private int user_id;

    // Schedule class를 인자로 가지는 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.schedule_id = schedule.getSchedule_id();
        this.schedule = schedule.getSchedule();
        this.name = schedule.getName();
        this.password = schedule.getPassword();
        this.date_post = schedule.getDate_post();
        this.date_correction = schedule.getDate_correction();
        this.user_id = schedule.getUser_id();
    }


}
