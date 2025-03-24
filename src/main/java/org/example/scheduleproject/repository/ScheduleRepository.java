package org.example.scheduleproject.repository;

import org.example.scheduleproject.dto.ScheduleResponseDto;
import org.example.scheduleproject.entity.Schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ScheduleRepository {
    void save(Schedule schedule); // Schedule 저장
    List<Schedule> findAllSchedule(); // List<Schedule> 반환
    Schedule findById(int schedule_id); // Schedule 반환
    void update(Schedule schedule); // Schedule 업데이트
    void delete(int schedule_id); // schedule_Id 로 삭제
}
