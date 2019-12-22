package com.spring.assistant.assistant.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author semih on Aralık, 2019, 19.12.2019, 22:55:34
 */

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InProgressEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String userId;

	private String inProgressTaskOne;

	private String inProgressTaskTwo;

	private boolean available;


}
