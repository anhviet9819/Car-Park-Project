package com.example.carparkproject.exception;

public class DuplicateIdException extends RuntimeException{
    public DuplicateIdException(String message) {
        super(message);
    }
}
