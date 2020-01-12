package com.spring.assistant.assistant.blog.statistic;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 12:45:27
 */
@Getter
@Setter
public class BodyStatisticModel {


	private String body;

	private Long bodyLength;

	private String mostCommonWords;

	private List<String> fetCommonWords;

	@Builder
	public BodyStatisticModel(String body, Long bodyLength, String mostCommonWords, List<String> fetCommonWords) {
		this.body = body;
		this.bodyLength = bodyLength;
		this.mostCommonWords = mostCommonWords;
		this.fetCommonWords = fetCommonWords;
	}
}
