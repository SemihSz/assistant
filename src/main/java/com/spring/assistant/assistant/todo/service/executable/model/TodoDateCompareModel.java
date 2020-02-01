package com.spring.assistant.assistant.todo.service.executable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author semih on Şubat, 2020, 1.02.2020, 23:38:42
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class TodoDateCompareModel {

	private String taskId;

	private String userId;

	private Integer differences;

}
