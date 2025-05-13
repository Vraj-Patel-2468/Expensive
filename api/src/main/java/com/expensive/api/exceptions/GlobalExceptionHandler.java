package com.expensive.api.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expensive.api.dto.ErrorResDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class) 
    public ResponseEntity<ErrorResDto> handleRuntimeExceptionClass(RuntimeException runtimeException) {
        ErrorResDto resDto = new ErrorResDto();
        resDto.setMessage(runtimeException.getMessage());
        resDto.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<> (resDto, HttpStatus.BAD_REQUEST);         
    }
}
