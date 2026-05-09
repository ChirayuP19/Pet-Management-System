package com.chirayu.flowgrid.exception;

import com.chirayu.flowgrid.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chirayu
 * @created 2026-05-01
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleOwnerNotFoundException(OwnerNotFoundException e) {
        log.error("OwnerNotFoundException: {}", e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage(),HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDTO> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED,HttpStatus.METHOD_NOT_ALLOWED.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAllExceptions(Exception e) {
        log.error("Exception: {}", e.getMessage());
        ErrorDTO errorDTO = new ErrorDTO(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
