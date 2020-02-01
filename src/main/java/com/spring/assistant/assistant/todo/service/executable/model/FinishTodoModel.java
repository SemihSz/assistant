package com.spring.assistant.assistant.todo.service.executable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author semih on Ocak, 2020, 28.01.2020, 21:34:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FinishTodoModel {

	private LocalDate finishDate;

	private LocalDate createdDate;

	private String userId;

	private String taskId;

	private String taskImportantLevel;

}
