package com.spring.assistant.assistant.blog.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 21:40:51
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PostUpdateRequest {


	private String commentId;

	private String title;

	private String body;

	private String category;


}
