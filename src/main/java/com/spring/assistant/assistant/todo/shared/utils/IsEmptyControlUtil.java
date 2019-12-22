package com.spring.assistant.assistant.todo.shared.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 12:33:29
 */
@NoArgsConstructor
@Component
public class IsEmptyControlUtil {


	public boolean isTwoInputIsNotEmpty(String s1, String s2) {

		return !StringUtils.isEmpty(s1) && !StringUtils.isEmpty(s2);

	}

	public boolean oneOfThemEmpty(String s1, String s2) {

		return StringUtils.isEmpty(s1) || StringUtils.isEmpty(s2);

	}

}
