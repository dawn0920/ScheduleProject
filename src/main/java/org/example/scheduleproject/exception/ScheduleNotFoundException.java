package org.example.scheduleproject.exception;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(String msg){
        super(msg);
    }
}
