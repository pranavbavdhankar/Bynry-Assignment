package com.example.StockFlow.exception;

import com.example.StockFlow.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFound(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new Response(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ResourceAlReadyExistException.class)
    public ResponseEntity<Response> resourceAlreadyExist(ResourceAlReadyExistException e) {
        return new ResponseEntity<>(
                new Response(HttpStatus.CONFLICT.value(), e.getMessage(), LocalDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

}
