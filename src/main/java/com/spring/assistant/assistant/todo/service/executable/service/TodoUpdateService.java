package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import com.spring.assistant.assistant.todo.service.executable.model.TodoUpdateModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 26.12.2019, 22:38:10
 */
@Service
@AllArgsConstructor
public class TodoUpdateService implements SimpleTask<TodoUpdateModel, TodoEntity> {

	private final TodoRepository todoRepository;


	@Override
	public TodoEntity apply(TodoUpdateModel todoUpdateModel) {

		final TodoEntity todoEntity = TodoEntity.builder()
				.taskId(todoUpdateModel.getTaskId())
				.category(todoUpdateModel.getCategory())
				.createdDate(todoUpdateModel.getCreatedDate())
				.description(todoUpdateModel.getDescription())
				.email(todoUpdateModel.getEmail())
				.expectFinishDate(todoUpdateModel.getFinishDate())
				.importantLevel(todoUpdateModel.getImportantLevel())
				.isFinnished(todoUpdateModel.isFinished())
				.title(todoUpdateModel.getTitle())
				.updatedDate(todoUpdateModel.getUpdateDate())
				.userId(todoUpdateModel.getUserId())
				.build();

		todoRepository.save(todoEntity);

		return null;
	}
}
