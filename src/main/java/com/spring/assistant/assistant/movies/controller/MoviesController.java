package com.spring.assistant.assistant.movies.controller;

import com.spring.assistant.assistant.movies.request.UserMoviesRequest;
import com.spring.assistant.assistant.movies.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:35:52
 */
@Controller
@RequestMapping("/movie-and-tv")
@AllArgsConstructor
public class MoviesController {

	private final MovieService movieService;

	@GetMapping(path = "/movies")
	public String getMovie(UserMoviesRequest userMoviesRequest, Model model) {
		model.addAttribute("userMoviesRequest", userMoviesRequest);
		return "movie/movies";
	}

	@PostMapping(path = "/save-movie")
	public String saveMoviesAndTv(UserMoviesRequest userMoviesRequest) {

		movieService.saveMovieUser(userMoviesRequest);

		return "redirect:/movie-and-tv/movies";
	}

	@RequestMapping(path = "/movie-list", method = RequestMethod.GET)
	public String showMoviesList(Model model) {
		model.addAttribute("movies", movieService.getUserMovieList());
		return "movie/show-movies";
	}


}
