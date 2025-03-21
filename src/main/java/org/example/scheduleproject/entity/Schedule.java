package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    private int schedule_id;
    private String schedule;
    private String name;
    private String password;
    private LocalDateTime date;



}
