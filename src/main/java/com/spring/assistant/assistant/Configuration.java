package com.spring.assistant.assistant;

import com.spring.assistant.assistant.todo.service.implemantation.SubTodoServiceIml;
import com.spring.assistant.assistant.todo.service.implemantation.TodoServiceIml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class Configuration {

    @Bean
    @Primary
    public TodoServiceIml todoServiceIml(){
        return new TodoServiceIml();
    }
    @Bean
    public SubTodoServiceIml subTodoServiceIml(){
        return new SubTodoServiceIml();
    }
}
