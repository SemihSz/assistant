package com.spring.assistant.assistant.movies.service.executable;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.movies.entity.UserMoviesEntity;
import com.spring.assistant.assistant.movies.model.UserMovieModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Aralık, 2019, 29.12.2019, 00:52:46
 */
@Service
@AllArgsConstructor
public class ConvertService implements SimpleTask<List<UserMoviesEntity>, List<UserMovieModel>> {


	@Override
	public List<UserMovieModel> apply(List<UserMoviesEntity> userMoviesEntities) {
		final List<UserMovieModel> userList = new ArrayList<>();

		for (UserMoviesEntity userMoviesEntity : userMoviesEntities) {
			final UserMovieModel userMovieModel = UserMovieModel.builder()
					.imageUrl(userMoviesEntity.getImageUrl())
					.numberOfWatchTime(userMoviesEntity.getNumberOfWatchTime())
					.lastWatchDate(userMoviesEntity.getLastWatchDate())
					.movieName(userMoviesEntity.getMovieName())
					.movieId(userMoviesEntity.getMovieId())
					.movieCategory(userMoviesEntity.getMovieCategory())
					.movieUserScore(userMoviesEntity.getMovieUserScore())
					.generalScore(userMoviesEntity.getGeneralScore())
					.userId(userMoviesEntity.getUserId())
					.movieTrailerLink(userMoviesEntity.getMovieTrailerLink())
					.build();

			userList.add(userMovieModel);
		}

		return userList;
	}
}
