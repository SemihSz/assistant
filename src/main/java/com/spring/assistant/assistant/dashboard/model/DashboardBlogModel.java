package com.spring.assistant.assistant.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author semih on Ocak, 2020, 25.01.2020, 21:40:20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardBlogModel {

	private Long allBlogLength;

	private Map<String, Integer> blogCategory;


}
