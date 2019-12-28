package com.spring.assistant.assistant.movies.service;

import com.spring.assistant.assistant.movies.request.UserMoviesRequest;
import com.spring.assistant.assistant.movies.response.MovieListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:45:53
 */
@Service
public interface MovieService {

	void saveMovieUser(UserMoviesRequest userMoviesRequest);

	List<MovieListResponse> getUserMovieList();
}
