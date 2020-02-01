package com.spring.assistant.assistant.dashboard.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author semih on Ocak, 2020, 21.01.2020, 22:02:29
 */
@Data
@Builder
public class DashboardDeleteModel {

	private Integer numberOfTodoDelete;

	private Integer numberOfSubTodoDelete;

}
