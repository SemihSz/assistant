package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.todo.entity.DeleteAllTodoEntity;
import com.spring.assistant.assistant.todo.repository.DeleteAllTodoRepository;
import com.spring.assistant.assistant.todo.service.executable.model.DeleteTodoAndSubTaskModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 26.12.2019, 21:56:25
 */
@AllArgsConstructor
@Service
public class DeleteTodoSaveService implements SimpleTask<DeleteTodoAndSubTaskModel, DeleteAllTodoEntity> {

	private final DeleteAllTodoRepository deleteAllTodoRepository;

	@Override
	public DeleteAllTodoEntity apply(DeleteTodoAndSubTaskModel deleteTodoAndSubTaskModel) {

		final DeleteAllTodoEntity allTodoEntity = DeleteAllTodoEntity.builder()
				.id(deleteTodoAndSubTaskModel.getId())
				.title(deleteTodoAndSubTaskModel.getTitle())
				.description(deleteTodoAndSubTaskModel.getDescription())
				.category(deleteTodoAndSubTaskModel.getCategory())
				.userId(deleteTodoAndSubTaskModel.getUserId())
				.createdDate(deleteTodoAndSubTaskModel.getCreatedDate())
				.importantLevel(deleteTodoAndSubTaskModel.getImportantLevel())
				.expectFinishDate(deleteTodoAndSubTaskModel.getExpectFinishDate())
				.isFinnished(deleteTodoAndSubTaskModel.getIsFinnished())
				.updatedDate(deleteTodoAndSubTaskModel.getUpdatedDate())
				.taskId(deleteTodoAndSubTaskModel.getTaskId())
				.todoPrefix(deleteTodoAndSubTaskModel.getTodoPrefix())
				.subTodoTitle(deleteTodoAndSubTaskModel.getSubTodoTitle())
				.subTodoDescription(deleteTodoAndSubTaskModel.getSubTodoDescription())
				.subTodoCategory(deleteTodoAndSubTaskModel.getSubTodoCategory())
				.subTodoCreatedDate(deleteTodoAndSubTaskModel.getSubTodoCreatedDate())
				.subTodoFinishDate(deleteTodoAndSubTaskModel.getSubTodoFinishDate())
				.subTodoUpdateDate(deleteTodoAndSubTaskModel.getSubTodoUpdateDate())
				.subTaskId(deleteTodoAndSubTaskModel.getSubTaskId())
				.build();


		return deleteAllTodoRepository.save(allTodoEntity);
	}
}
