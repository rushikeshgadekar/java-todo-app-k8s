package com.productivity.todoapp.todoservice.repository;

import com.productivity.todoapp.todoservice.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<ToDo, String> {
}
