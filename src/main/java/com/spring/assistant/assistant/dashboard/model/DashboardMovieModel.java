package com.spring.assistant.assistant.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author semih on Şubat, 2020, 8.02.2020, 12:43:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DashboardMovieModel {


	private Set<String> movieCategory;

	private Map<String, Long> countMovieCategory;

	private MovieCategoryResponse movieCategoryResponse;

	private Long maxCategoryCount;

	private Double averagePoint;

	private Map<String, Integer> numberOfMoviesCount;

	private Map<String, Integer> numberOfWatchTime;

	private Map<String, Date> dateAndMovie;


}
