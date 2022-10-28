package com.example.carparkproject.exception;

public class InvalidRequestBodyException extends RuntimeException{
    public InvalidRequestBodyException(String message) {
        super(message);
    }
}
