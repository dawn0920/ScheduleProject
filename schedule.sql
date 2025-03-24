use schedule;

create table `schedule`
(
    schedule_id     INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    schedule        VARCHAR(255) NOT NULL,
    name            VARCHAR(45)  NOT NULL,
    password        VARCHAR(255) NOT NULL,
    date_post       DATETIME     NOT NULL,
    date_correction DATETIME
);

ALTER TABLE schedule MODIFY COLUMN schedule_id INT NOT NULL AUTO_INCREMENT;

DESCRIBE schedule;

ALTER TABLE schedule AUTO_INCREMENT = 1;

create table users
(
    id              INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(45)  NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    date_post       DATETIME     NOT NULL,
    date_correction DATETIME
);


create table `schedule`
(
    schedule_id     INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    schedule        VARCHAR(255) NOT NULL,
    name            VARCHAR(45)  NOT NULL,
    password        VARCHAR(255) NOT NULL,
    date_post       DATETIME     NOT NULL,
    date_correction DATETIME,
    user_id         INT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);


