package com.spring.assistant.assistant.dashboard.service;

import com.spring.assistant.assistant.dashboard.model.MovieCategoryResponse;
import com.spring.assistant.assistant.dashboard.model.MovieModel;
import com.spring.assistant.assistant.interfaces.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author semih on Şubat, 2020, 8.02.2020, 22:24:05
 */
@Service
@Slf4j
@AllArgsConstructor
public class DashboardCalculateService implements Mapper<List<MovieModel>, Integer, MovieCategoryResponse> {

	@Override
	public MovieCategoryResponse apply(List<MovieModel> movieCategoryAverageScoreList, Integer userInput) {
		return getApply(movieCategoryAverageScoreList, userInput);
	}

	private MovieCategoryResponse getApply(List<MovieModel> movieCategoryAverageScoreList, Integer userInput) {

		final Map<String, Double> calculateMovieCategoryUserScoreAverage = movieCategoryAverageScoreList
				.stream()
				.collect(Collectors.groupingBy(MovieModel::getCategory,
						Collectors.averagingDouble(MovieModel::getCategoryAverageScore)));

		final Map<String, Double> averageUnderFiveMovie = calculateMovieCategoryUserScoreAverage
				.entrySet()
				.stream()
				.filter(e -> e.getValue() < 5.5)
				.collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue));

		final Map<String, Double> mostLikeMovieCategories = calculateMovieCategoryUserScoreAverage
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit((userInput > calculateMovieCategoryUserScoreAverage.size())
						? calculateMovieCategoryUserScoreAverage.size()
						: userInput)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));


		final MovieCategoryResponse movieCategoryResponse = MovieCategoryResponse.builder()
				.userWatchMovieAverage(calculateMovieCategoryUserScoreAverage)
				.userWatchMovieAverageUnderFive(averageUnderFiveMovie)
				.mostLikeMovieCategories(mostLikeMovieCategories)
				.build();

		return movieCategoryResponse;
	}
}
