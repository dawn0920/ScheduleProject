package org.example.scheduleproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int id;
    private String name;
    @Setter
    private String email;
    private LocalDate date_post;
    private LocalDate date_correction;

    public Users(String name, String email) {
        this.name = name;
        this.email = email;
        this.date_post = LocalDate.now();
        this.date_correction = null;
    }

}
