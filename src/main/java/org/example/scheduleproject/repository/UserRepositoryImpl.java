package org.example.scheduleproject.repository;

import org.example.scheduleproject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    // 의존성 주입
    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer findUserIdByEmail(String email) {
        try {
            String sql = "SELECT id FROM users WHERE email = ?";
            return jdbcTemplate.queryForObject(sql, Integer.class, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public int saveUser(Users user) {
        String sql =
                "Insert INTO users (name, email, date_post) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getDate_post());

        String lastSql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(lastSql, Integer.class);
    }

    @Override
    public Users findById(Integer user_id) {
        String sql =
                "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Users.class), user_id);
    }
}
