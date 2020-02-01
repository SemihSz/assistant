package com.spring.assistant.assistant.todo.shared.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author semih on Ocak, 2020, 21.01.2020, 23:18:35
 */
@Component
public class MapUtils {

	public Map<String, Integer> incrementValue(Map<String, Integer> map, String key) {

		Integer count = map.get(key);

		if (count == null) {
			count = 0;
		}
		map.put(key, count + 1);

		return map;
	}
}
