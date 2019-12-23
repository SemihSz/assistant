package com.spring.assistant.assistant.movies.service.implemantation;

import com.spring.assistant.assistant.executable.interfaces.service.GetUserIdService;
import com.spring.assistant.assistant.movies.model.UserMovieModel;
import com.spring.assistant.assistant.movies.request.UserMoviesRequest;
import com.spring.assistant.assistant.movies.service.MovieService;
import com.spring.assistant.assistant.movies.service.executable.UserSaveMovieService;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:55:46
 */
@Service
@AllArgsConstructor
public class UserMovieServiceImpl implements MovieService {

	private final GetUserIdService getUserIdService;

	private final GenerateNumberUtil generateNumberUtil;

	private final UserSaveMovieService userSaveMovieService;

	@Override
	public void saveMovieUser(UserMoviesRequest userMoviesRequest) {


		final UserMovieModel userMovieModel = UserMovieModel.builder()
				.movieUserScore(Integer.parseInt(userMoviesRequest.getMovieScore()))
				.movieCategory(userMoviesRequest.getCategory())
				.movieId(generateNumberUtil.generateUserId(7))
				.movieName(userMoviesRequest.getName())
				.userId(getUserIdService.getUserId())
				.lastWatchDate(userMoviesRequest.getDate())
				.numberOfWatchTime(Integer.parseInt(userMoviesRequest.getNumberOfWatch()))
				.imageUrl(userMoviesRequest.getImageUrl())
				.build();

		userSaveMovieService.apply(userMovieModel);


	}
}
