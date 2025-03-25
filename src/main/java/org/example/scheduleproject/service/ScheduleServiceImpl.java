package org.example.scheduleproject.service;

import org.example.scheduleproject.dto.*;
import org.example.scheduleproject.entity.Schedule;
import org.example.scheduleproject.entity.Users;
import org.example.scheduleproject.repository.ScheduleRepository;
import org.example.scheduleproject.repository.UserRepository;
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
    private final UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, UserRequestDto userRequestDto) {
        // 사용자가 존재하는지 확인 후 없으면 에러 반환
        Integer user_id = userRepository.findUserIdByEmail(userRequestDto.getEmail());

        if (user_id == null || user_id == 0) {
            Users newUser = new Users(userRequestDto.getName(), userRequestDto.getEmail());
            user_id = userRepository.saveUser(newUser);
        }

        Schedule schedule = new Schedule(
                requestDto.getSchedule(),
                requestDto.getName(),
                requestDto.getPassword(),
                user_id
        );

        int scheduleId = scheduleRepository.save(schedule, user_id);
        schedule.setSchedule_id(scheduleId);

        Users user = userRepository.findById(user_id);
        UserResponseDto userResponseDto = new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDate_post(),
                user.getDate_correction());

        return new ScheduleResponseDto(schedule, userResponseDto);
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
