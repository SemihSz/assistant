package com.spring.assistant.assistant.blog.service.implemantation;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.executable.service.BadgeStatisticService;
import com.spring.assistant.assistant.blog.executable.service.BodyStatisticService;
import com.spring.assistant.assistant.blog.executable.service.CreateNewBlogService;
import com.spring.assistant.assistant.blog.executable.service.GenerateBadgeService;
import com.spring.assistant.assistant.blog.executable.service.GetCurrentUserPostService;
import com.spring.assistant.assistant.blog.executable.service.StatisticBlogService;
import com.spring.assistant.assistant.blog.model.PostBodyModel;
import com.spring.assistant.assistant.blog.model.PostSaveModel;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.request.PostRequestModel;
import com.spring.assistant.assistant.blog.response.BadgeResponse;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import com.spring.assistant.assistant.blog.service.PostService;
import com.spring.assistant.assistant.blog.service.UploadService;
import com.spring.assistant.assistant.blog.statistic.BadgeStatisticModel;
import com.spring.assistant.assistant.blog.statistic.BodyStatisticModel;
import com.spring.assistant.assistant.blog.statistic.entity.StatisticEntity;
import com.spring.assistant.assistant.executable.interfaces.service.GetUserIdService;
import com.spring.assistant.assistant.general.GenerateService;
import com.spring.assistant.assistant.todo.shared.enums.PostStatusType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PostServiceImpl implements PostService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    private final GetUserIdService getUserIdService;

    private final PostRepository postRepository;

    private final GenerateService generateService;

    private final GenerateBadgeService generateBadgeService;

    private final UploadService uploadService;

    private final CreateNewBlogService createNewBlogServiceService;

    private final GetCurrentUserPostService getCurrentUserPostService;

    private final StatisticBlogService statisticBlogService;

    private final BadgeStatisticService badgeStatisticService;

    private final BodyStatisticService bodyStatisticService;

    @Override
    public PostEntity saveNewPost(PostRequestModel postRequestModel, MultipartFile multipartFile) {

        List<BadgeResponse> badgeList = separateBadge(postRequestModel.getBadgeAll());
        BadgeResponse badgeResponse = badgeList.get(0);
        logger.info("Now Creating the new post");

        String commentId = generateService.createCommentId();

        final PostSaveModel postSaveModel = PostSaveModel.builder()
                .title(postRequestModel.getTitle())
                .body(postRequestModel.getBody())
                .category(postRequestModel.getCategory())
                .createDate(new Date())
                .updatedDate(null)
                .userId(getUserIdService.getUserId())
                .commentId(commentId)
                .postStatusType(PostStatusType.NEW)
                .urlLink(postRequestModel.getUrlLink())
                .build();


        if (!multipartFile.isEmpty()) {

            logger.info("Now Starting the upload service!");
            uploadService.storeFile(multipartFile, commentId);
            logger.info("Now uploading image into db Comment id = {}, user id = {}", postSaveModel.getCommentId(), postSaveModel.getUserId());

        }

        logger.info("Comment id = {}, user id = {}", postSaveModel.getCommentId(), postSaveModel.getUserId());

        final PostEntity postEntity = createNewBlogServiceService.apply(postSaveModel,
                null, badgeResponse);

        logger.info("Save post into DB!");

        final BadgeStatisticModel badgeStatisticModel = badgeStatisticService.apply(badgeResponse);

        final PostBodyModel postBodyModel = PostBodyModel.builder()
                .postBody(postSaveModel.getBody())
                .build();

        final BodyStatisticModel bodyStatisticModel = bodyStatisticService.apply(postBodyModel);

        final StatisticEntity StatisticEntity = statisticBlogService.apply(postSaveModel,
                badgeStatisticModel, bodyStatisticModel);

        return postEntity;
    }

    private List<BadgeResponse> separateBadge(String badgeAll) {

        return generateBadgeService.apply(badgeAll);
    }

    @Override
    public List<PostEntity> showCurrentUserList() {

        return postRepository.findByUserId(getUserIdService.getUserId());
    }

    @Override
    public List<PostCurrentUserResponse> postCurrentUserResponse() {

        return getCurrentUserPostService.apply(showCurrentUserList());
    }

}
