package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.*;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.repository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    // 의존성 주입 (데이터베이스 작업을 위한 작업)
    // 이해 필요...
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getSchedule(),
                requestDto.getName(),
                requestDto.getPassword()
        );
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(ScheduleCheckRequestDto scheduleCheckRequestDto) {
        return List.of();
    }

    @Override
    public ResponseEntity<ScheduleResponseDto> findScheduleById(int schedule_id) {
        return null;
    }

    @Override
    public ResponseEntity<ScheduleResponseDto> updateSchedule(int schedule_id, ScheduleUpdateRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSchedule(int schedule_id, PasswordRequestDto passwordRequestDto) {
        return null;
    }

    // 인터페이스에서 정의한 메소드를 구현하는 클래스
    // 로직을 처리 (ex. 스케줄 추가, 조회, 수정, 삭제)
}
