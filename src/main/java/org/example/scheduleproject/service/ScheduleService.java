package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScheduleService {
    // 서비스 인터페이스는 비즈니스 로직을 정의한다 (각 메소드가 어떤 작업을 수행할 것인지 정의)
    // 첫 데이터 저장
//    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto);
    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, UserRequestDto userRequestDto);

    // 전체 일정 조회
    List<ScheduleResponseDto> findAllSchedule();

    // 선택 일정 조회
    ScheduleResponseDto findScheduleById(int schedule_id);

    // 선택 일정 수정
    ScheduleResponseDto updateSchedule(int schedule_id, ScheduleUpdateRequestDto requestDto);

    // 선택 일정 삭제
    void deleteSchedule(int schedule_id, String password);

}
