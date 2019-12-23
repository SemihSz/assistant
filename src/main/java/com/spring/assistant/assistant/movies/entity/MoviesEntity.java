package com.spring.assistant.assistant.movies.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 15:36:18
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MoviesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String movieId;

	private String movieName;

	private Date movieCreateDate;

	private Long movieGeneralScore;

	private String movieShortDescription;

	private String movieCategory;


}
