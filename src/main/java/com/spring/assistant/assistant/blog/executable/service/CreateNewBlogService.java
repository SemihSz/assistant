package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.model.PostSaveModel;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.response.BadgeResponse;
import com.spring.assistant.assistant.executable.interfaces.Mappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 01:00:53
 */

@Service
@AllArgsConstructor
@Slf4j
public class CreateNewBlogService implements Mappers<PostSaveModel, String, BadgeResponse, PostEntity> {

    private final PostRepository postRepository;

    @Override
    public PostEntity apply(PostSaveModel postSaveModel, String badgeAll, BadgeResponse badgeResponses) {

        badgeAll = null;

        final PostEntity postEntity = PostEntity.builder()
                .title(postSaveModel.getTitle())
                .body(postSaveModel.getBody())
                .category(postSaveModel.getCategory())
                .createDate(postSaveModel.getCreateDate())
                .updatedDate(postSaveModel.getUpdatedDate())
                .userId(postSaveModel.getUserId())
                .commentId(postSaveModel.getCommentId())
                .postStatusType(postSaveModel.getPostStatusType())
                .badgeOne(badgeResponses.getBadgeOne())
                .badgeTwo(badgeResponses.getBadgeTwo())
                .badgeThree(badgeResponses.getBadgeThree())
                .badgeFour(badgeResponses.getBadgeFour())
                .badgeFive(badgeResponses.getBadgeFive())
                .urlLink(postSaveModel.getUrlLink())
                .build();

        return postRepository.save(postEntity);
    }

}
