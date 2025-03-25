package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private int user_id;

    public Schedule(String schedule, String name, String password, int user_id){
        this.schedule = schedule;
        this.name = name;
        this.password = password;
        this.date_post = LocalDate.now(); // 작성 당시 날짜 입력
        this.date_correction = null;
        this.user_id = user_id;
    }

    public void update(String newSchedule){
        this.schedule = newSchedule;
        this.date_correction = LocalDate.now(); // 수정 당시 날짜 입력
    }

    // save이후 쿼리의 id값을 가져옴
    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public Schedule(int schedule_id, String schedule, String name, LocalDate date_post, LocalDate date_correction, int user_id) {
        this.schedule_id = schedule_id;
        this.schedule = schedule;
        this.name = name;  // 작성자 이름 추가
        this.date_post = date_post;
        this.date_correction = date_correction;
        this.user_id = user_id;
    }

}
