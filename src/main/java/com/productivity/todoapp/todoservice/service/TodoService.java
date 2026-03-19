package com.productivity.todoapp.todoservice.service;

import com.productivity.todoapp.todoservice.dto.ToDoDto;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    ToDoDto createTodo(ToDoDto toDoDto);

    List<ToDoDto> getAllTodos();

    ToDoDto getTodoById(String id);

    ToDoDto updateTodo(ToDoDto toDoDto);

    void deleteTodo(String id);
}
