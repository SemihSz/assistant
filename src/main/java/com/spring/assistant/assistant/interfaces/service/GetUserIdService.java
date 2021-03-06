package com.spring.assistant.assistant.interfaces.service;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.usercontroller.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 00:15:22
 */
@Service
@AllArgsConstructor
@Slf4j
public class GetUserIdService implements SimpleTask<String, String> {

	private final UserService userService;

	@Override
	public String apply(String userId) {
		return getUserId();
	}

	public String getUserId() {
		final String username = showEmailAddress();
		return userService.findByEmail(username).getUserId();

	}

	public String showEmailAddress() {

		return userService.giveUserAuthenticationInformation();

	}
}
