package com.spring.assistant.assistant.todo.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ImportantType {

	FIVE("Very Important"),
	FOUR("Important"),
	THREE("Neutral"),
	TWO("Not Neutral"),
	ONE("Not Important");

	@Getter
	@Setter
	public String importantLevelNum;

}
