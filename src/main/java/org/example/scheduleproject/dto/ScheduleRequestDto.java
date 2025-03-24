package org.example.scheduleproject.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {
    private String schedule;
    private String name;
    private String password;
    private LocalDateTime date_post;
    private LocalDateTime date_correction;
}
