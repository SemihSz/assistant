package com.spring.assistant.assistant.blog.statistic;

import com.spring.assistant.assistant.general.model.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 12:42:27
 */
@Getter
@Setter
public class BadgeStatisticModel extends BaseModel {

	private String badgeResponse;

	@Builder
	public BadgeStatisticModel(String userId, String badgeResponse) {
		super(userId);
		this.badgeResponse = badgeResponse;
	}
}
