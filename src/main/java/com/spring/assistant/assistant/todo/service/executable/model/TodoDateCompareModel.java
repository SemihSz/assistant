package com.spring.assistant.assistant.todo.service.executable.model;

import com.spring.assistant.assistant.todo.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author semih on Şubat, 2020, 1.02.2020, 23:38:42
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class TodoDateCompareModel {

	private Integer passExpectedDate;

	private Integer notPassExpectedDate;

	private Integer isEqual;

	private List<TodoEntity> passList;

	private List<TodoEntity> notPassList;

	private List<TodoEntity> equalList;

}
