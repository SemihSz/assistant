package com.spring.assistant.assistant.general.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author semih on Ocak, 2020, 30.01.2020, 22:43:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class KeyAndValue {

	private String key;

	private String value;
}
