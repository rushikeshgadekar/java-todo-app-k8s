package com.productivity.todoapp.todoservice.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage<T> {
    private String status;
    private String message;
    @Builder.Default
    private LocalDateTime timeStamp = LocalDateTime.now();
    private T data;
}
