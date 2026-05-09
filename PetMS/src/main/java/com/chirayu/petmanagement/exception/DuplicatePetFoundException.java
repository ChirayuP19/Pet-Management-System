package com.chirayu.petmanagement.exception;

public class DuplicatePetFoundException extends RuntimeException {
    public DuplicatePetFoundException(String message) {
        super(message);
    }
}
