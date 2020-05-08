package com.spring.assistant.assistant.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author semih on Şubat, 2020, 8.02.2020, 23:11:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCategoryResponse {

	private Map<String, Double> userWatchMovieAverage;

	private Map<String, Double> userWatchMovieAverageUnderFive;

	private Map<String, Double> mostLikeMovieCategories;
}
