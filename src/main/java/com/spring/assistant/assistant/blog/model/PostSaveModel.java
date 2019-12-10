package com.spring.assistant.assistant.blog.model;

import com.spring.assistant.assistant.general.model.BaseModel;
import com.spring.assistant.assistant.todo.shared.enums.PostStatusType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author semih on Aralık, 2019
 */

@Getter
@Setter
public class PostSaveModel extends BaseModel {

	@NotNull
	private String title;

	@NotNull
	private String body;

	@NotNull
	private String category;

	private Date createDate;

	private Date updatedDate;

	private String commentId;

	private PostStatusType postStatusType;

	private String badgeOne;

	private String badgeTwo;

	private String badgeThree;

	private String badgeFour;

	private String badgeFive;

	private String urlLink;

	@Builder
	public PostSaveModel(@NotNull String userId, @NotNull String title, @NotNull String body, @NotNull String category,
	                     Date createDate, Date updatedDate, String commentId, PostStatusType postStatusType, String badgeOne,
	                     String badgeTwo, String badgeThree, String badgeFour, String badgeFive, String urlLink) {
		super(userId);
		this.title = title;
		this.body = body;
		this.category = category;
		this.createDate = createDate;
		this.updatedDate = updatedDate;
		this.commentId = commentId;
		this.postStatusType = postStatusType;
		this.badgeOne = badgeOne;
		this.badgeTwo = badgeTwo;
		this.badgeThree = badgeThree;
		this.badgeFour = badgeFour;
		this.badgeFive = badgeFive;
		this.urlLink = urlLink;
	}
}
