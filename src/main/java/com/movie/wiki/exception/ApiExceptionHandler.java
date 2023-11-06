package com.movie.wiki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = IdNotFound.class)
    public ResponseEntity<ExceptionDto> handleException(IdNotFound e){
        ExceptionDto dto = new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
