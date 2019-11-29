package com.spring.assistant.assistant.todo.service;

import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.model.request.TodoRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoSortRequestModel;
import com.spring.assistant.assistant.todo.model.request.TodoTaskIdRequestModel;
import com.spring.assistant.assistant.todo.shared.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    TodoDto createNewTodo(TodoDto todo);
    TodoDto getUser(String userId);
    TodoDto specialNewTodo(TodoDto todoDto);
    TodoDto createNewSubTodoTask(TodoDto todo);
    TodoDto updateSpecifTask(TodoRequestModel todoRequestModel);
    TodoDto finishTodo(TodoTaskIdRequestModel todoTaskIdRequestModel);
    void deleteSpecificTodo(TodoTaskIdRequestModel todoTaskIdRequestModel);
    void deleteAll();
    List<TodoEntity> showTodoList();
    List<TodoEntity> showTodoListCurrentUser();
    List<TodoEntity> showSpecifTodoAndSubTask(String taskId);

    List<TodoEntity> getlAllTodosWithPagination(TodoSortRequestModel todoSortRequestModel);

    void automaticEmailService();

    List<TodoEntity> sortAllTodos();
    //TODO delete, update, show, put,

}
