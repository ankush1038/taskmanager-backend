package com.taskmanager.exception;

import com.taskmanager.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex)
    {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex)
    {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}