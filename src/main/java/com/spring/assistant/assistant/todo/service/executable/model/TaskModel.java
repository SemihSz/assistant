package com.spring.assistant.assistant.todo.service.executable.model;

import com.spring.assistant.assistant.general.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author semih on Aralık, 2019, 26.12.2019, 21:02:08
 */
@Getter
@Setter
public class TaskModel extends BaseModel {

	private String taskId;

	private boolean isFinished;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate finishDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate updateDate;

	public TaskModel(String userId, String taskId, boolean isFinished, LocalDate createdDate, LocalDate finishDate, LocalDate updateDate) {
		super(userId);
		this.taskId = taskId;
		this.isFinished = isFinished;
		this.createdDate = createdDate;
		this.finishDate = finishDate;
		this.updateDate = updateDate;
	}
}
