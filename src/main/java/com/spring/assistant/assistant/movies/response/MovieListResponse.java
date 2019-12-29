package com.spring.assistant.assistant.movies.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author semih on Aralık, 2019, 28.12.2019, 22:47:14
 */
@Data
@Builder
public class MovieListResponse {


	private String movieUrl;

	private String movieName;

	private String movieGeneralScore;

	private String movieId;

	private String movieCategory;

	private Double userMovieScore;

	private Integer numberOfWatchTime;

	private Date releaseDate;

	private LocalDate lastWatchDate;

	private String movieTrailerLink;

}
