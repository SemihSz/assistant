package com.spring.assistant.assistant.blog.statistic;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 12:41:33
 */

@Getter
@Setter
public class BlogUserStatisticModel extends BadgeStatisticModel {

	private String category;

	private Date createdDate;

	private Date updatedDate;

	private BodyStatisticModel bodyStatisticModel;


	public BlogUserStatisticModel(String userId, String badgeResponse, String category, Date createdDate, Date updatedDate, BodyStatisticModel bodyStatisticModel) {
		super(userId, badgeResponse);
		this.category = category;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.bodyStatisticModel = bodyStatisticModel;
	}
}
