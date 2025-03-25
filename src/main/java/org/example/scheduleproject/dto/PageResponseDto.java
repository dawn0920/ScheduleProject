package org.example.scheduleproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto {
    private int page;  // 현재 페이지 번호
    private int size;  // 전체 페이지 수
    private int offset;
}
