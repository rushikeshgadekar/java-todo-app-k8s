package com.productivity.todoapp.todoservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDto {

    private String id;

    @NotBlank(message = "Todo must not be empty. Enter valid Todo")
    @Size(min = 3, max = 255, message = "Todo must be between 3 to 255 characters")
    private String todo;
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
}
