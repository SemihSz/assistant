package com.spring.assistant.assistant.movies.service.executable;

import com.spring.assistant.assistant.executable.interfaces.BasicTask;
import com.spring.assistant.assistant.movies.entity.UserMoviesEntity;
import com.spring.assistant.assistant.movies.model.UserMovieModel;
import com.spring.assistant.assistant.movies.repository.UserMoviesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:46:57
 */
@AllArgsConstructor
@Service
public class UserSaveMovieService implements BasicTask<UserMovieModel, UserMoviesEntity> {

	private final UserMoviesRepository userMoviesRepository;

	@Override
	public UserMoviesEntity apply(UserMovieModel userMovieModel) {

		final UserMoviesEntity userMoviesEntity = UserMoviesEntity.builder()
				.userId(userMovieModel.getUserId())
				.movieUserScore(userMovieModel.getMovieUserScore())
				.lastWatchDate(userMovieModel.getLastWatchDate())
				.movieCategory(userMovieModel.getMovieCategory())
				.movieName(userMovieModel.getMovieName())
				.movieId(userMovieModel.getMovieId())
				.imageUrl(userMovieModel.getImageUrl())
				.numberOfWatchTime(userMovieModel.getNumberOfWatchTime())
				.build();

		return userMoviesRepository.save(userMoviesEntity);
	}
}
