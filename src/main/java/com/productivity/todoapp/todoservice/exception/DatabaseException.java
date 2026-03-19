package com.productivity.todoapp.todoservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DatabaseException extends RuntimeException {
    private final HttpStatus httpStatus;

    public DatabaseException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
