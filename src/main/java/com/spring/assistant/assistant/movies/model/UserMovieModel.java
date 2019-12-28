package com.spring.assistant.assistant.movies.model;

import com.spring.assistant.assistant.general.model.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:47:43
 */
@Getter
@Setter
public class UserMovieModel extends BaseModel {

	private String movieName;

	private String movieCategory;

	private double movieUserScore;

	private double generalScore;

	private int numberOfWatchTime;

	private String movieId;

	private Date lastWatchDate;

	private String imageUrl;

	@Builder
	public UserMovieModel(String userId, String movieName, String movieCategory, double movieUserScore, double generalScore,
	                      int numberOfWatchTime, String movieId, Date lastWatchDate, String imageUrl) {
		super(userId);
		this.movieName = movieName;
		this.movieCategory = movieCategory;
		this.movieUserScore = movieUserScore;
		this.numberOfWatchTime = numberOfWatchTime;
		this.movieId = movieId;
		this.lastWatchDate = lastWatchDate;
		this.imageUrl = imageUrl;
		this.generalScore = generalScore;
	}
}
