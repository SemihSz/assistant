package com.spring.assistant.assistant.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Şubat, 2020, 8.02.2020, 22:30:07
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MovieModel {

	private String category;

	private Double categoryAverageScore;
}
