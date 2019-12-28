package com.spring.assistant.assistant.movies.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:36:28
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserMoviesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String movieName;

	private String movieCategory;

	private double movieUserScore;

	private double generalScore;

	private int numberOfWatchTime;

	private String userId;

	private String movieId;

	private Date lastWatchDate;

	private String imageUrl;

}
