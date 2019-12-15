package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.response.BadgeResponse;
import com.spring.assistant.assistant.exception.MaximumSizeException;
import com.spring.assistant.assistant.exception.ResourceNotFoundException;
import com.spring.assistant.assistant.executable.interfaces.BasicTask;
import com.spring.assistant.assistant.todo.shared.enums.BadgeIndexType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Aralık, 2019
 */

@Service
@Slf4j
public class GenerateBadgeService implements BasicTask<String, List<BadgeResponse>> {

	private static final Logger logger = LoggerFactory.getLogger(GenerateBadgeService.class);

	@Override
	public List<BadgeResponse> apply(String badgeAll) {
		List<BadgeResponse> responses = new ArrayList<>();

		if (badgeAll == null || badgeAll.equals("")) {

			logger.error("!! Badge is null. This process stop before separate process start!! badge={}", badgeAll);
			throw new ResourceNotFoundException("Badge is null. This process stop before separate process start");

		}

		String[] arr = badgeAll.split(" ");

		if (arr.length > 5) {

			logger.error("!! Badge maximum capacity is 5. This process stop before separate process start!! arrayList lenght={}", arr.length);
			throw new MaximumSizeException("Reach the max. of badge size. You have to remove some bage!");

		}

		List<String> list = new ArrayList<>();

		try {
			for (int i = 1; i <= 5; i++) {
				if (arr.length >= i) {
					list.add(arr[i - 1].substring(0, 1).toUpperCase() + arr[i - 1].substring(1).toLowerCase());
				}
				if (arr.length == i) {
					for (int j = 1; j <= (5 - arr.length); j++) {
						list.add("");
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		BadgeResponse badgeResponse = BadgeResponse.builder()
				.badgeOne(list.get(BadgeIndexType.ZERO.getCode()))
				.badgeTwo(list.get(BadgeIndexType.ONE.getCode()))
				.badgeThree(list.get(BadgeIndexType.TWO.getCode()))
				.badgeFour(list.get(BadgeIndexType.THREE.getCode()))
				.badgeFive(list.get(BadgeIndexType.FOUR.getCode()))
				.build();

		responses.add(badgeResponse);
		return responses;
	}
}
