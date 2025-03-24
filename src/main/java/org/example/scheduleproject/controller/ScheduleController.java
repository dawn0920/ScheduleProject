package org.example.scheduleproject.controller;

import org.example.scheduleproject.dto.ScheduleRequestDto;
import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Schedule schedule = new Schedule(scheduleId, requestDto.getSchedule(), requestDto.getName(), requestDto.getPassword(), requestDto.getDate_post(), requestDto.getDate_correction());

        // Inmemory DB에 객체 저장
        scheduleList.put(scheduleId, schedule);

        return new ScheduleResponseDto(schedule);

    }

    // 전체 일정 조회 기본(조건에 따라 일정 불러오는 쿼리도 추가)
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule() {

        // init LIst
        List<ScheduleResponseDto> responseList = new ArrayList<>();

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
            @RequestBody ScheduleRequestDto requestDto
    ) {
        Schedule schedule = scheduleList.get(schedule_id);

        // NPE 방지
        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 필수값 검증
        if(requestDto.getSchedule() == null ||
            requestDto.getName() == null ||
            requestDto.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 일정 수정
        schedule.update(requestDto);

        // 응답
        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    // 선택한 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int schedule_id) {
        // 리스트의 key 값에 id를 포함하고 있다면?
        if(scheduleList.containsKey(schedule_id)) {
            // key가 id인 value 삭제
            scheduleList.remove(schedule_id);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        // 포함하고 있지 않은 경우
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
