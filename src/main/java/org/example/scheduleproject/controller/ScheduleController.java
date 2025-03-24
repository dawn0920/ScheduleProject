package org.example.scheduleproject.controller;

import org.example.scheduleproject.dto.*;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 이해 필요
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule() {
        List<ScheduleResponseDto> schedules  = scheduleService.findAllSchedule();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // 선택 일정 조회
    @GetMapping("/{schedule_id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable int schedule_id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findScheduleById(schedule_id);
        return scheduleResponseDto != null
                ? new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 선택한 일정 수정
    @PutMapping("{schedule_id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable int schedule_id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(schedule_id, requestDto);
        return scheduleResponseDto != null ? new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK)
                                            : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 선택한 일정 삭제
    @DeleteMapping("/{schedule_id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable int schedule_id,
            @RequestBody PasswordRequestDto passwordRequestDto
    ) {
        scheduleService.deleteSchedule(schedule_id, passwordRequestDto.getPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
