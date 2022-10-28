package com.example.carparkproject.exception;

public class FullSlotException extends RuntimeException{
    public FullSlotException(String message) {
        super(message);
    }
}
