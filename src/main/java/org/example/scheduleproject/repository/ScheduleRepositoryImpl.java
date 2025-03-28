package org.example.scheduleproject.repository;

import org.example.scheduleproject.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    /* 조금 더 이해 필요 */
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleRepositoryImpl(JdbcTemplate  jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Schedule schedule, int user_id) {
        String sql =
                // "Insert INTO schedule (schedule, name, password, date_post) VALUES (?, ?, ?, ?)";
                "Insert INTO schedule (schedule, name, password, date_post, user_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, schedule.getSchedule(), schedule.getName(), schedule.getPassword(), schedule.getDate_post(), user_id);

        // 마지막으로 삽입된 ID를 가지고 오기 위한 SQL
        String lastSql = "SELECT LAST_INSERT_ID()";
        // queryForObject()를 사용하여 마지막으로 삽입된 레코드의 ID 값을 가져옴
        int increment_id = jdbcTemplate.queryForObject(lastSql, Integer.class);

        // 삽입된 schedule 객체에 자동 증가된 ID 값을 설정
        schedule.setSchedule_id(increment_id);

        // 값을 반환
        return increment_id;
    }

    @Override
    public List<Schedule> findAllSchedule() {
        String sql =
                "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, this::mapRowToSchedule);
    }

    public List<Schedule> findAllSchedule(int size, int offset) {
        String sql = "SELECT s.*, u.name user_name FROM schedule s " +
                "JOIN users u ON s.user_id = u.id " +
                "ORDER BY s.date_post " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Schedule(
                rs.getInt("schedule_id"),
                rs.getString("schedule"),
                rs.getString("user_name"), // 작성자 이름
                rs.getString("password"),
                rs.getTimestamp("date_post").toLocalDateTime().toLocalDate(),
                rs.getString("date_correction") != null ? rs.getTimestamp("date_correction").toLocalDateTime().toLocalDate() : null,
                rs.getInt("user_id")
        ), size, offset);
    }

    public List<Schedule> findAllSchedule(int size, int offset, LocalDate dateCorrection, String userName) {
        StringBuilder sql = new StringBuilder("SELECT s.*, u.name AS user_name FROM schedule s " +
                "JOIN users u ON s.user_id = u.id ");

        List<Object> params = new ArrayList<>();

        // 수정일 조건
        if (dateCorrection != null) {
            sql.append("WHERE DATE(s.date_correction) = ? ");
            params.add(dateCorrection);
        }

        // 작성자명 조건
        if (userName != null && !userName.isEmpty()) {
            if (params.isEmpty()) {
                sql.append("WHERE u.name = ? ");
            } else {
                sql.append("AND u.name = ? ");
            }
            params.add(userName);
        }

        // 수정일 기준 내림차순 정렬
        sql.append("ORDER BY s.date_correction DESC ");

        // 페이징 처리
        sql.append("LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        // SQL 실행
        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> new Schedule(
                rs.getInt("schedule_id"),
                rs.getString("schedule"),
                rs.getString("user_name"),
                rs.getString("password"),
                rs.getTimestamp("date_post").toLocalDateTime().toLocalDate(),
                rs.getString("date_correction") != null ? rs.getTimestamp("date_correction").toLocalDateTime().toLocalDate() : null,
                rs.getInt("user_id")
        ));
    }

    @Override
    public Schedule findById(int schedule_id) {
        try {
            String sql =
                    "SELECT * FROM schedule WHERE schedule_id = ?";
            return jdbcTemplate.queryForObject(sql, this::mapRowToSchedule, schedule_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public void update(Schedule schedule) {
        String sql =
                "UPDATE schedule SET schedule = ?, password = ?, date_correction = ? WHERE schedule_id = ?";
        jdbcTemplate.update(sql, schedule.getSchedule(), schedule.getPassword(), schedule.getDate_correction(), schedule.getSchedule_id());
    }

    @Override
    public void delete(int schedule_id) {
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";
        jdbcTemplate.update(sql, schedule_id);
    }

    @Override
    public List<Schedule> findAllScheduleByUserId(int user_id) {
        String sql =
             "SELECT * FROM schedule WHERE user_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToSchedule, user_id);
    }

    private Schedule mapRowToSchedule(ResultSet rs, int rowNum)
        throws SQLException {
        return new Schedule(
                rs.getInt("schedule_id"),
                rs.getString("schedule"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getTimestamp("date_post").toLocalDateTime().toLocalDate(),
                rs.getString("date_correction") != null ? rs.getTimestamp("date_correction").toLocalDateTime().toLocalDate() : null,
                rs.getInt("user_id")
        );
    }

}
