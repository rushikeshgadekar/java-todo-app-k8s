package com.productivity.todoapp.todoservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ToDoNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;
    public ToDoNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
