package com.productivity.todoapp.todoservice.exception;

import com.productivity.todoapp.todoservice.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ToDoNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleToDoException(ToDoNotFoundException exception, HttpServletRequest request) {
        ApiErrorResponse errorResponse
                = new ApiErrorResponse(
                UUID.randomUUID().toString(),
                exception.getMessage(),
                exception.getHttpStatus().value(),
                exception.getHttpStatus().name(),
                request.getRequestURI(),
                request.getMethod());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseException(DatabaseException exception, HttpServletRequest request) {
        ApiErrorResponse errorResponse
                = new ApiErrorResponse(UUID.randomUUID().toString(),
                exception.getMessage(),
                exception.getHttpStatus().value(),
                exception.getHttpStatus().name(),
                request.getRequestURI(),
                request.getMethod());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    Spring Boot automatically throws a MethodArgumentNotValidException when validation fails.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<FieldError> allErrors = exception.getBindingResult().getFieldErrors();
        HashMap<String, String> errors = new HashMap<>();
        allErrors.forEach(objectError -> {
            errors.put(objectError.getField(), objectError.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
