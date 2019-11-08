package com.spring.assistant.assistant.todo.service;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    TodoDto createNewTodo(TodoDto todo);
    TodoDto getUser(String userId);
    TodoDto specialNewTodo(TodoDto todoDto);
    TodoDto createNewSubTodoTask(TodoDto todo);
    List<TodoEntity> showTodoList();
    List<TodoEntity> showSpecifTodoAndSubTask(String taskId);

    //TODO delete, update, show, put,

}
