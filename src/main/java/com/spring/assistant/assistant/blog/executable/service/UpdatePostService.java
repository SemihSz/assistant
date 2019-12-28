package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.model.PostSaveModel;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import com.spring.assistant.assistant.interfaces.SimpleTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 22:06:38
 */
@AllArgsConstructor
@Service
@Slf4j
public class UpdatePostService implements SimpleTask<PostSaveModel, PostCurrentUserResponse> {

	private final PostRepository postRepository;

	@Override
	public PostCurrentUserResponse apply(PostSaveModel postSaveModel) {


		final PostEntity postEntity = PostEntity.builder()
				.title(postSaveModel.getTitle())
				.body(postSaveModel.getBody())
				.category(postSaveModel.getCategory())
				.createDate(postSaveModel.getCreateDate())
				.updatedDate(postSaveModel.getUpdatedDate())
				.userId(postSaveModel.getUserId())
				.commentId(postSaveModel.getCommentId())
				.postStatusType(postSaveModel.getPostStatusType())
				.badgeOne(postSaveModel.getBadgeOne())
				.badgeTwo(postSaveModel.getBadgeTwo())
				.badgeThree(postSaveModel.getBadgeThree())
				.badgeFour(postSaveModel.getBadgeFour())
				.badgeFive(postSaveModel.getBadgeFive())
				.urlLink(postSaveModel.getUrlLink())
				.build();

		//Todo log koy


		postRepository.save(postEntity);

		final PostCurrentUserResponse postCurrentUserResponse = PostCurrentUserResponse.builder()
				.title(postEntity.getTitle())
				.body(postEntity.getBody())
				.category(postEntity.getCategory())
				.createDate(postEntity.getCreateDate())
				.updatedDate(postEntity.getUpdatedDate())
				.commentId(postEntity.getCommentId())
				.postStatusType(postEntity.getPostStatusType())
				.badgeOne(postEntity.getBadgeOne())
				.badgeTwo(postEntity.getBadgeTwo())
				.badgeThree(postEntity.getBadgeThree())
				.badgeFour(postEntity.getBadgeFour())
				.badgeFive(postEntity.getBadgeFive())
				.urlLink(postEntity.getUrlLink())
				.build();

		return postCurrentUserResponse;
	}

}
