package com.spring.assistant.assistant.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author semih on Ocak, 2020, 21.01.2020, 23:11:31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {

	private String categoryName;

	private Integer countOfCategory;
}
