package com.spring.assistant.assistant.todo.shared;

import com.spring.assistant.assistant.blog.model.BodyMap;

import java.util.Comparator;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 16:53:21
 */
public class SortByRoll implements Comparator<BodyMap> {

	@Override
	public int compare(BodyMap o1, BodyMap o2) {
		return o1.getCount() - o2.getCount();
	}
}
