package com.spring.assistant.assistant.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 14:08:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BodyMap {

	private String name;

	private Integer count;

}
