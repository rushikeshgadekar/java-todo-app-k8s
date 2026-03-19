package com.productivity.todoapp.todoservice.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Data : A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor
 */
@Data
public class ApiErrorResponse {
    private final String errorId;      // Unique global identifier of the error, this field is useful for searching errors in a large log.
    private final String message;      // General description of an error message.
    private final int statusCode;      // HTTP status code.
    private final String statusName;   // HTTP status full name (e.g., NOT_FOUND)
    private final String path;         // URI of the resource where the error occurred.
    private final String httpMethod;   // Used HTTP method (e.g., GET, POST)
    private final LocalDateTime timeStamp = LocalDateTime.now(); // Timestamp at which the error occurred.
}
