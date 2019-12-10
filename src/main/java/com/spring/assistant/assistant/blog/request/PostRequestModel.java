package com.spring.assistant.assistant.blog.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestModel {

	private String title;

	private String body;

	private String category;

	private String badgeAll;

	private String urlLink;

}
