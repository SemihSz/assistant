package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.service.executable.model.FinishTodoModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Ocak, 2020, 28.01.2020, 21:56:02
 */
@Slf4j
@Service
@AllArgsConstructor
public class FinishTodoService implements SimpleTask<List<TodoEntity>, List<FinishTodoModel>> {

	@Override
	public List<FinishTodoModel> apply(List<TodoEntity> todoEntities) {
		return finishModel(todoEntities);
	}

	private List<FinishTodoModel> finishModel(List<TodoEntity> todoEntities) {

		final List<FinishTodoModel> finishTodoModels = new ArrayList<>();

		for (TodoEntity todoEntity : todoEntities) {
			final FinishTodoModel finishTodoModel = FinishTodoModel.builder()
					.finishDate(todoEntity.getExpectFinishDate())
					.createdDate(todoEntity.getCreatedDate())
					.taskId(todoEntity.getTaskId())
					.taskImportantLevel(todoEntity.getImportantLevel())
					.userId(todoEntity.getUserId())
					.build();

			finishTodoModels.add(finishTodoModel);
		}

		return finishTodoModels;
	}
}
