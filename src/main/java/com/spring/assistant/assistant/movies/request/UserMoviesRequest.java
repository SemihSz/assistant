package com.spring.assistant.assistant.movies.request;

import com.spring.assistant.assistant.general.model.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:36:51
 */
@Getter
@Setter
public class UserMoviesRequest extends BaseModel {

	private String name;

	private String movieScore;

	private String userMovieScore;

	private String numberOfWatch;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	private String category;

	private String imageUrl;

	private String trailerUrl;

	@Builder
	public UserMoviesRequest(String userId, String name, String numberOfWatch, Date date, String category,
	                         String imageUrl, String movieScore, String userMovieScore, String trailerUrl) {
		super(userId);
		this.name = name;
		this.movieScore = movieScore;
		this.numberOfWatch = numberOfWatch;
		this.date = date;
		this.category = category;
		this.imageUrl = imageUrl;
		this.userMovieScore = userMovieScore;
		this.trailerUrl = trailerUrl;
	}
}
