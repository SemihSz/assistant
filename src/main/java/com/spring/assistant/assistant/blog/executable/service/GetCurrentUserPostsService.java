package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import com.spring.assistant.assistant.executable.interfaces.BasicTask;
import com.spring.assistant.assistant.executable.interfaces.service.GetUserIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 11:06:15
 */
@AllArgsConstructor
@Service
public class GetCurrentUserPostsService implements BasicTask<List<PostEntity>, List<PostCurrentUserResponse>> {


	private final PostRepository postRepository;

	private final GetUserIdService getUserIdService;

	@Override
	public List<PostCurrentUserResponse> apply(List<PostEntity> postEntities) {

		List<PostEntity> getCurrentPosts = postRepository.findByUserId(getUserIdService.getUserId());
		List<PostCurrentUserResponse> newResponse = new ArrayList<>();
		for (PostEntity post : getCurrentPosts) {

			PostCurrentUserResponse postCurrentUserResponse = PostCurrentUserResponse.builder()
					.title(post.getTitle())
					.body(post.getBody())
					.commentId(post.getCommentId())
					.postStatusType(post.getPostStatusType())
					.attachFile(post.getCategory())
					.createDate(post.getCreateDate())
					.updatedDate(post.getUpdatedDate())
					.badgeOne(post.getBadgeOne())
					.badgeTwo(post.getBadgeTwo())
					.badgeThree(post.getBadgeThree())
					.badgeFour(post.getBadgeFour())
					.badgeFive(post.getBadgeFive())
					.urlLink(post.getUrlLink())
					.build();

			newResponse.add(postCurrentUserResponse);
		}
		return newResponse;
	}
}
