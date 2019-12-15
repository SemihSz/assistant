package com.spring.assistant.assistant.blog.response;

import com.spring.assistant.assistant.todo.shared.enums.PostStatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PostCurrentUserResponse {

	private String title;

	private String body;

	private String attachFile;

	private Date createDate;

	private String category;

	private Date updatedDate;

	private String commentId;

	private PostStatusType postStatusType;

	private String badgeOne;

	private String badgeTwo;

	private String badgeThree;

	private String badgeFour;

	private String badgeFive;

	private String urlLink;

	private String badgeAll;
}
