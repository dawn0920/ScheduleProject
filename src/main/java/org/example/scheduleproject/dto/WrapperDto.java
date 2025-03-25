package org.example.scheduleproject.dto;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class WrapperDto {
    @Valid
    private ScheduleRequestDto requestDto;

    @Valid
    private UserRequestDto userRequestDto;
}
