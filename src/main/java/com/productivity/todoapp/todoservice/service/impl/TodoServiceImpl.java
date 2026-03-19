package com.productivity.todoapp.todoservice.service.impl;

import com.productivity.todoapp.todoservice.dto.ToDoDto;
import com.productivity.todoapp.todoservice.entity.ToDo;
import com.productivity.todoapp.todoservice.exception.DatabaseException;
import com.productivity.todoapp.todoservice.exception.ToDoNotFoundException;
import com.productivity.todoapp.todoservice.mapper.ToDoMapper;
import com.productivity.todoapp.todoservice.repository.TodoRepository;
import com.productivity.todoapp.todoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public ToDoDto createTodo(ToDoDto toDoDto) {
        ToDo toDo = dtoToEntity(toDoDto);
        try {
            todoRepository.save(toDo);
            log.info("Todo with ID : {} Created Successfully", toDo.getId());
        } catch (DataAccessException exception) {
            log.error("Failed to save ToDo due to Database Error : {} : {}", HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            throw new DatabaseException("Failed to save ToDo due to Database Error", exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entityToDto(toDo);
    }

    @Override
    public List<ToDoDto> getAllTodos() {
        List<ToDo> allTodos = todoRepository.findAll();
        List<ToDoDto> todos = allTodos.stream().map(this::entityToDto).toList();
        return todos;
    }

    @Override
    public ToDoDto getTodoById(String id) {
        Optional<ToDo> todo = todoRepository.findById(id);
        log.info("Todo with ID : {} Fetched successfully.", id);
        return ToDoMapper.INSTANCE.mapOptionalToDto(todo);
    }

    @Override
    public ToDoDto updateTodo(ToDoDto toDoDto) {
        ToDo toDo = dtoToEntity(toDoDto);
        ToDo existingTodo = todoRepository.findById(toDo.getId()).orElseThrow(() -> new ToDoNotFoundException("Todo With Given Id : " + toDo.getId() + " does not Exist.", HttpStatus.NOT_FOUND));
        existingTodo.setTodo(toDo.getTodo());
        existingTodo.setDescription(toDo.getDescription());
        ToDo updatedTodo = todoRepository.save(existingTodo);
        log.info("Todo with ID : {} Updated Successfully", updatedTodo.getId());
        return entityToDto(updatedTodo);
    }

    @Override
    public void deleteTodo(String id) {
        ToDo existingTodo = todoRepository.findById(id).orElseThrow(() -> new ToDoNotFoundException("Todo With Given Id : " + id + " does not Exist.", HttpStatus.NOT_FOUND));
        todoRepository.delete(existingTodo);
        log.info("Todo with ID : {} Deleted Successfully", id);
    }

    private ToDoDto entityToDto(ToDo toDo) {
        return ToDoMapper.INSTANCE.entityToDto(toDo);
    }

    private ToDo dtoToEntity(ToDoDto toDoDto) {
        return ToDoMapper.INSTANCE.dtoToEntity(toDoDto);
    }
}
