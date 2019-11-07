package com.spring.assistant.assistant.todo.service;

import com.spring.assistant.assistant.todo.model.request.SubTodoRequestModel;
import com.spring.assistant.assistant.todo.shared.SubTodoDto;
import org.springframework.stereotype.Service;

@Service("subtodo")
public interface SubTodoService {

    SubTodoDto createNewSubTodo(SubTodoRequestModel subTodoRequestModel);
}
