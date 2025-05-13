package com.expensive.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResDto {
    private LocalDateTime timeStamp;
    private String message;
}
