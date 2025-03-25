package org.example.scheduleproject.repository;

import org.example.scheduleproject.entity.Users;

public interface UserRepository {
    Integer findUserIdByEmail(String email);
    int saveUser(Users user);
    Users findById(Integer user_id);
}
