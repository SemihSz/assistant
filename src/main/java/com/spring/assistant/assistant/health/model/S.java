package com.spring.assistant.assistant.health.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author semih on Mart, 2020, 15.03.2020, 21:56:32
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class S {

	private Date date;
	private Long confirmed;
	private Long deaths;
	private Long recovered;
}
