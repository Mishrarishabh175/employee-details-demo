package com.dpw.employeedetails.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) { super(message); }
    public NotFoundException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
