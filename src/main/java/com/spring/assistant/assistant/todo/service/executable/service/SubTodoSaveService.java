package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import com.spring.assistant.assistant.todo.repository.SubTodoRepository;
import com.spring.assistant.assistant.todo.service.executable.model.SubTaskSaveModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 26.12.2019, 20:58:29
 */
@Service
@AllArgsConstructor
public class SubTodoSaveService implements SimpleTask<SubTaskSaveModel, SubTodoEntity> {

	private final SubTodoRepository subTodoRepository;

	@Override
	public SubTodoEntity apply(SubTaskSaveModel subTaskSaveModel) {

		final SubTodoEntity subTodoEntity = SubTodoEntity.builder()
				.subTaskId(subTaskSaveModel.getSubTaskId())
				.subTodoCategory(subTaskSaveModel.getSubTodoCategory())
				.subTodoDescription(subTaskSaveModel.getSubTodoDescription())
				.subTodoTitle(subTaskSaveModel.getSubTodoTitle())
				.subTodoCreatedDate(subTaskSaveModel.getCreatedDate())
				.subTodoFinishDate(subTaskSaveModel.getFinishDate())
				.subTodoUpdateDate(subTaskSaveModel.getUpdateDate())
				.isFinished(subTaskSaveModel.isFinished())
				.taskId(subTaskSaveModel.getTaskId())
				.userId(subTaskSaveModel.getUserId())
				.build();

		return subTodoRepository.save(subTodoEntity);
	}
}
