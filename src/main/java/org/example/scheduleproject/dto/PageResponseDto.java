package org.example.scheduleproject.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponseDto {
    private int page;  // 현재 페이지 번호
    private int size;   // 전체 페이지 수
    private List<ScheduleResponseDto> schedules;  // 일정 목록

    public PageResponseDto(int page, int size, List<ScheduleResponseDto> schedules) {
        this.page = page;
        this.size = size;
        this.schedules = schedules;
    }
}
