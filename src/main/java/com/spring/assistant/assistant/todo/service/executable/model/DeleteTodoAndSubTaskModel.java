package com.spring.assistant.assistant.todo.service.executable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author semih on Aralık, 2019, 26.12.2019, 21:54:29
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DeleteTodoAndSubTaskModel {

	private Long id;

	private String title;

	private String description;

	private String category;

	private String userId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;

	private String importantLevel;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expectFinishDate;

	private Boolean isFinnished;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate updatedDate;

	private String taskId;

	private String subTodoTitle;

	private String subTodoDescription;

	private String subTodoCategory;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate subTodoCreatedDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate subTodoFinishDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate subTodoUpdateDate;

	private String subTaskId;

	private String todoPrefix;
}
