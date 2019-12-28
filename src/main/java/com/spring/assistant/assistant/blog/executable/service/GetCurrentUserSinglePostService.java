package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import com.spring.assistant.assistant.interfaces.SimpleTask;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 22:09:34
 */
@AllArgsConstructor
@Service
public class GetCurrentUserSinglePostService implements SimpleTask<String, PostCurrentUserResponse> {

	private final PostRepository postRepository;

	@Override
	public PostCurrentUserResponse apply(String commentId) {

		final PostEntity postEntity = postRepository.findByCommentId(commentId);

		final PostCurrentUserResponse userResponse = PostCurrentUserResponse.builder()
				.title(postEntity.getTitle())
				.body(postEntity.getBody())
				.commentId(postEntity.getCommentId())
				.category(postEntity.getCategory())
				.postStatusType(postEntity.getPostStatusType())
				.attachFile(postEntity.getCategory())
				.createDate(postEntity.getCreateDate())
				.updatedDate(postEntity.getUpdatedDate())
				.badgeOne(postEntity.getBadgeOne())
				.badgeTwo(postEntity.getBadgeTwo())
				.badgeThree(postEntity.getBadgeThree())
				.badgeFour(postEntity.getBadgeFour())
				.badgeFive(postEntity.getBadgeFive())
				.urlLink(postEntity.getUrlLink())
				.build();

		//todo log koy
		postRepository.deleteById(postEntity.getId());

		return userResponse;
	}
}
