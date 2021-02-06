package com.spring.assistant.assistant;

import com.spring.assistant.assistant.todo.service.implemantation.SubTodoServiceIml;
import com.spring.assistant.assistant.todo.service.implemantation.TodoServiceIml;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;


@ComponentScan(basePackages = "com.spring.assistant")
@SpringBootApplication
@EnableConfigurationProperties
public class AssistantApplication implements CommandLineRunner {

	private final BotService botService;

	public AssistantApplication(BotService botService) {
		this.botService = botService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}

	@Bean(name = "SubTodoServiceIml")
	public SubTodoServiceIml subTodoServiceIml() {
		return new SubTodoServiceIml();
	}

	@Bean(name = "TodoServiceIml")
	@Primary
	public TodoServiceIml serviceIml() {
		return new TodoServiceIml();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		botService.startBot();
	}
}
