package com.spring.assistant.assistant.todo.service;

import com.spring.assistant.assistant.todo.shared.SubTodoDto;
import org.springframework.stereotype.Service;

@Service
public interface SubTodoService {

    SubTodoDto createNewSubTodo(SubTodoDto subTodoDto);
}
