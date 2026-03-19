package com.productivity.todoapp.todoservice.controller;

import com.productivity.todoapp.todoservice.dto.ToDoDto;
import com.productivity.todoapp.todoservice.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
public class WebController {

    private final TodoService todoService;

    @Operation(summary = "Show home page")
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @Operation(summary = "Show all todos")
    @GetMapping("/todos")
    public String showAllTodos(Model model) {
        List<ToDoDto> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @Operation(summary = "Delete a todo")
    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable("id") String id, Model model) {
        try {
            todoService.deleteTodo(id);
            model.addAttribute("deleteMessage", "Todo deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("deleteError", "Failed to delete todo: " + e.getMessage());
        }
        // Reload the todos list
        List<ToDoDto> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @Operation(summary = "Show create todo form")
    @GetMapping({"/create", "/create/"})
    public String showCreateForm(Model model) {
        model.addAttribute("todoDto", new ToDoDto());
        return "create-todo";
    }

    @Operation(summary = "Create a new ToDo from form")
    @PostMapping({"/create", "/create/"})
    public String createTodoFromForm(@Valid @ModelAttribute("todoDto") ToDoDto toDoDto, Model model) {
        try {
            ToDoDto createdToDo = todoService.createTodo(toDoDto);
            model.addAttribute("message", "Todo created successfully!");
            model.addAttribute("todo", createdToDo);
            model.addAttribute("todoDto", new ToDoDto()); // Reset form
            return "create-todo";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create todo: " + e.getMessage());
            model.addAttribute("todoDto", toDoDto); // Keep form data
            return "create-todo";
        }
    }
}