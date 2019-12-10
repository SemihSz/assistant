package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.response.BadgeResponse;
import com.spring.assistant.assistant.blog.statistic.BadgeStatisticModel;
import com.spring.assistant.assistant.executable.interfaces.BasicTask;
import com.spring.assistant.assistant.executable.interfaces.service.GetUserIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 13:09:05
 */
@Service
@AllArgsConstructor
public class BadgeStatisticService implements BasicTask<BadgeResponse, BadgeStatisticModel> {

	private final GetUserIdService getUserIdService;

	@Override
	public BadgeStatisticModel apply(BadgeResponse badgeResponse) {

		return BadgeStatisticModel.builder()
				.badgeResponse(badgeResponse.getBadgeOne() + " " +
						badgeResponse.getBadgeTwo() + " " +
						badgeResponse.getBadgeThree() + " " +
						badgeResponse.getBadgeFour() + " " +
						badgeResponse.getBadgeFive())
				.userId(getUserIdService.getUserId())
				.build();

	}
}

