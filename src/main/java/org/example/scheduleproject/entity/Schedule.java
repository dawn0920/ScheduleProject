package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.scheduleproject.dto.ScheduleRequestDto;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private int schedule_id;
    private String schedule;
    private String name;
    private String password;
    private LocalDateTime date_post;
    private LocalDateTime date_correction;

    public void update(ScheduleRequestDto requestDto){
        this.schedule = schedule;
        this.date_correction = date_correction;
    }
}
