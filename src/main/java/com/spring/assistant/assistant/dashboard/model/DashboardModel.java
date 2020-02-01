package com.spring.assistant.assistant.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author semih on Ocak, 2020, 24.01.2020, 22:34:16
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardModel {

	private Integer allTodo;

	private Integer numberOfTodo;

	private Integer numberOfBlog;

	private Integer numberOfMovies;

	private Integer numberOfSubTodo;

	private Integer numberOfProgressTodo;

	private Integer numberOfFinishTodo;

	private Map<String, Long> importantLevelCount;

	private DashboardBlogModel dashboardBlogModel;

	private UserAllInformationModel setUserAllInf;

	private Integer passExpectedDateSize;

}
