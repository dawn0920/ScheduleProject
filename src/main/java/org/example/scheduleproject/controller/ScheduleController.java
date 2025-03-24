package org.example.scheduleproject.controller;

import org.example.scheduleproject.dto.*;
import org.example.scheduleproject.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 후에 DB에 저장하는 구도는 다시 구해야함.
    private final Map<Integer, Schedule> scheduleList = new HashMap<>();

    // 처음 데이터 저장
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // id 값이 1씩 증가함
        int scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        // 요청받은 데이터로 객체 생성
        Schedule schedule = new Schedule(
                scheduleId,
                requestDto.getSchedule(),
                requestDto.getName(),
                requestDto.getPassword());

        // Inmemory DB에 객체 저장
        scheduleList.put(scheduleId, schedule);

        return new ScheduleResponseDto(schedule);

    }

    // 전체 일정 조회 (조건에 따라 일정 불러오는 쿼리도 추가)
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(
            @RequestBody ScheduleCheckRequstDto scheduleCheckRequstDto
    ) {

        // init LIst
        List<ScheduleResponseDto> responseList = new ArrayList<>();

        if (scheduleCheckRequstDto.getName() == null &&
                scheduleCheckRequstDto.getDate_correction() == null) {

        }

        // for문을 이용해 list값 꺼내기
        for (Schedule schedule : scheduleList.values()) {
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }
        return responseList;
    }

    // 선택 일정 조회
    @GetMapping("/{schedule_id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable int schedule_id) {

        // 직별자에 스케줄이 없다면
        Schedule schedule = scheduleList.get(schedule_id);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    // 선택한 일정 수정
    @PutMapping("{schedule_id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable int schedule_id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        Schedule schedule = scheduleList.get(schedule_id);

        // NPE 방지
        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 필수값 검증
        if(requestDto.getScheduleRequestDto().getSchedule() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 비밀번호가 일치하지 않으면 반환
        if(!schedule.getPassword().equals(requestDto.getPasswordRequestDto().getPassword())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // 일정 수정
        schedule.update(requestDto.getScheduleRequestDto().getSchedule());

        // 응답
        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    // 선택한 일정 삭제
    @DeleteMapping("/{schedule_id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable int schedule_id,
            @RequestBody PasswordRequestDto passwordRequestDto
    ) {
        Schedule schedule = scheduleList.get(schedule_id);

        // NPE 방지
        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!schedule.getPassword().equals(passwordRequestDto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        scheduleList.remove(schedule_id);

        // 포함하고 있지 않은 경우
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
