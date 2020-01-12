package com.spring.assistant.assistant.todo.service.executable.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author semih on Aralık, 2019, 26.12.2019, 22:27:42
 */
@Getter
@Setter
public class TodoUpdateModel extends TaskModel {

	private String title;

	private String description;

	private String category;

	private String importantLevel;

	private String email;

	@Builder
	public TodoUpdateModel(String userId, String taskId, boolean isFinished, LocalDate createdDate, LocalDate finishDate, String email,
	                       LocalDate updateDate, String title, String description, String category, String importantLevel) {
		super(userId, taskId, isFinished, createdDate, finishDate, updateDate);
		this.title = title;
		this.description = description;
		this.category = category;
		this.importantLevel = importantLevel;
		this.email = email;
	}


}
