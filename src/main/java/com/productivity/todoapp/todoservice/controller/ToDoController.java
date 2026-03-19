package com.productivity.todoapp.todoservice.controller;

import com.productivity.todoapp.todoservice.dto.ToDoDto;
import com.productivity.todoapp.todoservice.response.ApiResponseMessage;
import com.productivity.todoapp.todoservice.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final TodoService todoService;

    @Operation(summary = "Create a new ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo Created Successfully."),
            @ApiResponse(responseCode = "500", description = "Invalid Request Data")
    })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseMessage<ToDoDto>> addTodo(@Valid @RequestBody ToDoDto toDoDto) {
        ToDoDto createdToDo = todoService.createTodo(toDoDto);
        ApiResponseMessage<ToDoDto> response = ApiResponseMessage.<ToDoDto>builder()
                .status("Success")
                .message("Todo Created")
                .data(createdToDo)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all Todos", description = "Fetch a list of all Todos")
    @GetMapping("/todos")
    public ResponseEntity<ApiResponseMessage<List<ToDoDto>>> todos() {
        List<ToDoDto> todos = todoService.getAllTodos();
        ApiResponseMessage<List<ToDoDto>> response = ApiResponseMessage.<List<ToDoDto>>builder()
                .message("All Todos")
                .status("Success")
                .data(todos)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Fetch Todo with ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMessage<ToDoDto>> getTodoById(@PathVariable String id) {
        ToDoDto todo = todoService.getTodoById(id);
        ApiResponseMessage<ToDoDto> response = ApiResponseMessage.<ToDoDto>builder()
                .message("Todo")
                .status("Success")
                .data(todo)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an existing Todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo Updated Successfully."),
            @ApiResponse(responseCode = "404", description = "Todo With Given Id does not Exist.")
    })
    @PutMapping("/update")
    public ResponseEntity<ApiResponseMessage<ToDoDto>> update(@Valid @RequestBody ToDoDto toDoDto) {
        ToDoDto updatedTodo = todoService.updateTodo(toDoDto);
        ApiResponseMessage<ToDoDto> response = ApiResponseMessage.<ToDoDto>builder()
                .message("Todo Updated")
                .status("Success")
                .data(updatedTodo)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Todo with ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseMessage<String>> delete(@PathVariable String id) {
        todoService.deleteTodo(id);
        ApiResponseMessage<String> response = ApiResponseMessage.<String>builder()
                .message("Todo Deleted")
                .status("Success")
                .build();
        return ResponseEntity.ok(response);
    }
}
