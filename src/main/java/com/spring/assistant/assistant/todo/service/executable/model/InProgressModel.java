package com.spring.assistant.assistant.todo.service.executable.model;

import com.spring.assistant.assistant.general.model.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Aralık, 2019, 19.12.2019, 22:53:40
 */

@Getter
@Setter
public class InProgressModel extends BaseModel {

	private String inProgressTaskOne;

	private String inProgressTaskTwo;

	private boolean available;

	@Builder
	public InProgressModel(String userId, String inProgressTaskOne, String inProgressTaskTwo, boolean available) {
		super(userId);
		this.inProgressTaskOne = inProgressTaskOne;
		this.inProgressTaskTwo = inProgressTaskTwo;
		this.available = available;
	}
}
