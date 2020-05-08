package com.spring.assistant.assistant.dashboard.request;

import com.spring.assistant.assistant.general.model.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Şubat, 2020, 9.02.2020, 00:10:29
 */

@Getter
@Setter
public class DashboardRequest extends BaseModel {

	private Integer userInput;

	@Builder
	public DashboardRequest(String userId, Integer userInput) {
		super(userId);
		this.userInput = userInput;
	}
}
