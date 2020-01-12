package com.spring.assistant.assistant.movies.service.executable;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.movies.model.UserMovieModel;
import com.spring.assistant.assistant.movies.repository.UserMoviesRepository;
import com.spring.assistant.assistant.movies.response.MovieListResponse;
import com.spring.assistant.assistant.todo.shared.utils.CalculateAverageUtil;
import com.spring.assistant.assistant.todo.shared.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Aralık, 2019, 28.12.2019, 22:39:04
 */
@Service
@AllArgsConstructor
public class GetMovieListService implements SimpleTask<String, List<MovieListResponse>> {

	private static DecimalFormat df2 = new DecimalFormat("#.##");
	private final UserMoviesRepository userMoviesRepository;
	private final ConvertService convertService;
	private final CalculateAverageUtil calculateAverageUtil;

	@Override
	public List<MovieListResponse> apply(String userId) {

		final List<MovieListResponse> responseUserMovieList = new ArrayList<>();
		final List<UserMovieModel> userMovieList = convertService.apply(userMoviesRepository.findByUserId(userId));

		for (UserMovieModel movies : userMovieList) {

			double generalScore = calculateAverageUtil.getBothScore(movies.getGeneralScore(), movies.getMovieUserScore());

			final MovieListResponse movieListResponse = MovieListResponse.builder()
					.movieCategory(movies.getMovieCategory())
					.movieId(movies.getMovieId())
					.movieName(movies.getMovieName())
					.movieUrl(movies.getImageUrl())
					.userMovieScore(movies.getMovieUserScore())
					.numberOfWatchTime(movies.getNumberOfWatchTime())
					.lastWatchDate(DateUtils.asLocalDate(movies.getLastWatchDate()))
					.movieGeneralScore(String.valueOf(df2.format(generalScore)))
					.movieTrailerLink(movies.getMovieTrailerLink())
					.build();

			responseUserMovieList.add(movieListResponse);

		}

		return responseUserMovieList;
	}
}
