package com.chirayu.petmanagement.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        HttpStatus status,
        Integer code,
        LocalDateTime timestamp
) {
}
