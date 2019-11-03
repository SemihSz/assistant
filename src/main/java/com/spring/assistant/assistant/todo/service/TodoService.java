package com.spring.assistant.assistant.todo.service;

import com.spring.assistant.assistant.todo.shared.TodoDto;
import org.springframework.stereotype.Service;

@Service
public interface TodoService {

    TodoDto createNewTodo(TodoDto todo);
    TodoDto getUser(String userId);
    TodoDto specialNewTodo(TodoDto todoDto);
    TodoDto createNewSubTodoTask(TodoDto todo);
    //TODO delete, update, show, put,

}
