package com.spring.assistant.assistant.todo.service.executable.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * @author semih on Aralık, 2019, 26.12.2019, 20:59:39
 */
@Getter
@Setter
public class SubTaskSaveModel extends TaskModel {


	private String subTodoTitle;

	private String subTodoDescription;

	private String subTodoCategory;

	private String subTaskId;

	@Builder
	public SubTaskSaveModel(String userId, String taskId, boolean isFinished, LocalDate createdDate, LocalDate finishDate,
	                        LocalDate updateDate, String subTodoTitle, String subTodoDescription, String subTodoCategory, String subTaskId) {
		super(userId, taskId, isFinished, createdDate, finishDate, updateDate);
		this.subTodoTitle = subTodoTitle;
		this.subTodoDescription = subTodoDescription;
		this.subTodoCategory = subTodoCategory;
		this.subTaskId = subTaskId;
	}
}
