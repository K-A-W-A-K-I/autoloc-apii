package tn.esprit.autoloc.autolocapi.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String STATUS_KEY = "status";
    private static final String ERROR_KEY = "error";
    private static final String MESSAGE_KEY = "message";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(TIMESTAMP_KEY, LocalDateTime.now(Clock.systemUTC()));
        errorResponse.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
        errorResponse.put(ERROR_KEY, "Not Found");
        errorResponse.put(MESSAGE_KEY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(TIMESTAMP_KEY, LocalDateTime.now(Clock.systemUTC()));
        errorResponse.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
        errorResponse.put(ERROR_KEY, "Bad Request");
        errorResponse.put(MESSAGE_KEY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(TIMESTAMP_KEY, LocalDateTime.now(Clock.systemUTC()));
        errorResponse.put(STATUS_KEY, HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put(ERROR_KEY, "Internal Server Error");
        errorResponse.put(MESSAGE_KEY, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
