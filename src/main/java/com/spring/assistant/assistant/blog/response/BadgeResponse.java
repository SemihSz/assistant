package com.spring.assistant.assistant.blog.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeResponse {

	private String badgeOne;

	private String badgeTwo;

	private String badgeThree;

	private String badgeFour;

	private String badgeFive;

	@Builder
	public BadgeResponse(String badgeOne, String badgeTwo, String badgeThree, String badgeFour, String badgeFive) {
		this.badgeOne = badgeOne;
		this.badgeTwo = badgeTwo;
		this.badgeThree = badgeThree;
		this.badgeFour = badgeFour;
		this.badgeFive = badgeFive;
	}
}
