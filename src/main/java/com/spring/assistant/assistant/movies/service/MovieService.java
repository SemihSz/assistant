package com.spring.assistant.assistant.movies.service;

import com.spring.assistant.assistant.movies.request.UserMoviesRequest;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:45:53
 */
@Service
public interface MovieService {

	void saveMovieUser(UserMoviesRequest userMoviesRequest);
}
