package org.example.scheduleproject.repository;

import org.example.scheduleproject.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    /* 조금 더 이해 필요 */
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(JdbcTemplate  jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Schedule schedule) {
        String sql =
                "Insert INTO schedule (schedule, name, password, date_post) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, schedule.getSchedule(), schedule.getName(), schedule.getPassword(), schedule.getDate_post());
    }

    @Override
    public List<Schedule> findAllSchedule() {
        String sql =
                "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, this::mapRowToSchedule);
    }

    @Override
    public Schedule findById(int schedule_id) {
        String sql =
                "SELECT * FROM schedule WHERE schedule_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToSchedule, schedule_id);
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

    private Schedule mapRowToSchedule(ResultSet rs, int rowNum)
        throws SQLException {
        return new Schedule(
                rs.getInt("schedule_id"),
                rs.getString("schedule"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getTimestamp("date_post").toLocalDateTime().toLocalDate(),
                rs.getString("date_correction") != null ? rs.getTimestamp("date_correction").toLocalDateTime().toLocalDate() : null
        );
    }

}
