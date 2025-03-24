package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.scheduleproject.dto.ScheduleRequestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private int schedule_id;
    private String schedule;
    private String name;
    private String password;
    private LocalDate date_post;
    private LocalDate date_correction;

    public Schedule(int schedule_id, String schedule, String name, String password){
        this.schedule_id = schedule_id;
        this.schedule = schedule;
        this.name = name;
        this.password = password;
        this.date_post = LocalDate.now(); // 작성 당시 날짜 입력
        this.date_correction = null;
    }

    public void update(String newSchedule){
        this.schedule = newSchedule;
        this.date_correction = LocalDate.now(); // 수정 당시 날짜 입력
    }
}
