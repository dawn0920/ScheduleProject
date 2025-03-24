package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.*;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 인터페이스에서 정의한 메소드를 구현하는 클래스
// 로직을 처리 (ex. 스케줄 추가, 조회, 수정, 삭제)
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
    public List<ScheduleResponseDto> findAllSchedule() {
        List<Schedule> schedules = scheduleRepository.findAllSchedule();
        return schedules.stream()
                .map(schedule -> new ScheduleResponseDto(schedule))
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto findScheduleById(int schedule_id) {
        Schedule schedule = scheduleRepository.findById(schedule_id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(int schedule_id, ScheduleUpdateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(schedule_id);

        if (schedule == null ||
                !schedule.getPassword().equals(requestDto.getPasswordRequestDto().getPassword())) {
            return null;
        }

        // 일정 수정
        schedule.update(requestDto.getScheduleRequestDto().getSchedule());
        scheduleRepository.update(schedule);
        // 응답
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(int schedule_id, String password) {
        Schedule schedule = scheduleRepository.findById(schedule_id);

        if (schedule != null && schedule.getPassword().equals(password)) {
            scheduleRepository.delete(schedule_id);
        }
    }
}
