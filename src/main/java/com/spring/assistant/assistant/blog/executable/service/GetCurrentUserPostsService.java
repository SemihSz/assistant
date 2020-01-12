package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.interfaces.service.GetUserIdService;
import com.spring.assistant.assistant.todo.shared.utils.DateUtils;
import com.spring.assistant.assistant.usercontroller.User;
import com.spring.assistant.assistant.usercontroller.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 11:06:15
 */
@AllArgsConstructor
@Service
public class GetCurrentUserPostsService implements SimpleTask<List<PostEntity>, List<PostCurrentUserResponse>> {


	private final PostRepository postRepository;

	private final GetUserIdService getUserIdService;

	@Autowired
	UserService userService;

	@Override
	public List<PostCurrentUserResponse> apply(List<PostEntity> postEntities) {

		List<PostEntity> getCurrentPosts = postRepository.findByUserId(getUserIdService.getUserId());
		List<PostCurrentUserResponse> newResponse = new ArrayList<>();
		for (PostEntity post : getCurrentPosts) {

			final User user = userService.findByEmail(getUserIdService.showEmailAddress());

			PostCurrentUserResponse postCurrentUserResponse = PostCurrentUserResponse.builder()
					.id(post.getId())
					.title(post.getTitle())
					.body(post.getBody())
					.commentId(post.getCommentId())
					.postStatusType(post.getPostStatusType())
					.attachFile(post.getCategory())
					.createDate(DateUtils.asLocalDate(post.getCreateDate()))
					.updatedDate(post.getUpdatedDate())
					.badgeOne(post.getBadgeOne())
					.badgeTwo(post.getBadgeTwo())
					.badgeThree(post.getBadgeThree())
					.badgeFour(post.getBadgeFour())
					.badgeFive(post.getBadgeFive())
					.urlLink(post.getUrlLink())
					.userName(user.getFirstName())
					.imageUrlLink(post.getUrlImageLink())
					.build();

			newResponse.add(postCurrentUserResponse);
		}
		return newResponse;
	}
}
