package com.spring.assistant.assistant.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author semih on Şubat, 2020, 25.02.2020, 22:07:35
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class NotificationTodoModel {


	private String title;

	private String description;

	private LocalDate date;
}
