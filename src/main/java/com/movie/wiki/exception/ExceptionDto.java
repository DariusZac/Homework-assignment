package com.movie.wiki.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ExceptionDto {
    private final String message;
    private final HttpStatus httpstatus;
    private final LocalDateTime timestamp;
}
