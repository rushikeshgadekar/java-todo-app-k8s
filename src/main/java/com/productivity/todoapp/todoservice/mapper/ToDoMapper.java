package com.productivity.todoapp.todoservice.mapper;

import com.productivity.todoapp.todoservice.dto.ToDoDto;
import com.productivity.todoapp.todoservice.entity.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface ToDoMapper {

    ToDoMapper INSTANCE = Mappers.getMapper(ToDoMapper.class);

    // Entity to DTO Mapper.
    ToDoDto entityToDto(ToDo toDo);

    // DTO to Entity Mapper.
    ToDo dtoToEntity(ToDoDto toDoDto);

    default ToDoDto mapOptionalToDto(Optional<ToDo> optionalToDo) {
        ToDoDto toDoDto = null;
        if (optionalToDo.isPresent()) {
            toDoDto = entityToDto(optionalToDo.get());
        }
        return toDoDto;
    }
}
