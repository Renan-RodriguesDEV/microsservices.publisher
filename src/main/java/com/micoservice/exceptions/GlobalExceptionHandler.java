package com.micoservice.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("Status", "Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException e) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("Status", "Error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExistsException(AlreadyExistsException e) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("Status", "Error");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
