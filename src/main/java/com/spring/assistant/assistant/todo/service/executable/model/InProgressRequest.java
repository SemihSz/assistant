package com.spring.assistant.assistant.todo.service.executable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Aralık, 2019, 20.12.2019, 00:19:57
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class InProgressRequest {


	private String inProgressTaskOne;

	private String inProgressTaskTwo;

}
