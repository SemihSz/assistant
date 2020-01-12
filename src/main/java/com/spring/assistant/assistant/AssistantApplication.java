package com.spring.assistant.assistant;

import com.spring.assistant.assistant.todo.service.implemantation.SubTodoServiceIml;
import com.spring.assistant.assistant.todo.service.implemantation.TodoServiceIml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;


@ComponentScan(basePackages = "com.spring.assistant")
@SpringBootApplication
public class AssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}
	@Bean(name = "SubTodoServiceIml")
	public SubTodoServiceIml subTodoServiceIml(){return new SubTodoServiceIml();}
	@Bean(name = "TodoServiceIml")
	@Primary
	public TodoServiceIml serviceIml(){return new TodoServiceIml();}
}
