package com.spring.assistant.assistant.blog.service.implemantation;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.executable.service.BadgeStatisticService;
import com.spring.assistant.assistant.blog.executable.service.BodyStatisticService;
import com.spring.assistant.assistant.blog.executable.service.CreateNewBlogService;
import com.spring.assistant.assistant.blog.executable.service.GenerateBadgeService;
import com.spring.assistant.assistant.blog.executable.service.GetCurrentUserPostsService;
import com.spring.assistant.assistant.blog.executable.service.GetCurrentUserSinglePostService;
import com.spring.assistant.assistant.blog.executable.service.StatisticBlogService;
import com.spring.assistant.assistant.blog.executable.service.UpdatePostService;
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
import com.spring.assistant.assistant.general.GenerateService;
import com.spring.assistant.assistant.interfaces.service.GetUserIdService;
import com.spring.assistant.assistant.todo.shared.enums.PostStatusType;
import com.spring.assistant.assistant.todo.shared.utils.DateUtils;
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

    private final PostRepository postRepository;

    private final GetUserIdService getUserIdService;

    private final GenerateService generateService;

    private final GenerateBadgeService generateBadgeService;

    private final UploadService uploadService;

    private final CreateNewBlogService createNewBlogServiceService;

    private final GetCurrentUserPostsService getCurrentUserPostsService;

    private final StatisticBlogService statisticBlogService;

    private final BadgeStatisticService badgeStatisticService;

    private final BodyStatisticService bodyStatisticService;

    private final GetCurrentUserSinglePostService getCurrentUserSinglePostService;

    private final UpdatePostService updatePostService;

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
                .imageUrlLink(postRequestModel.getImageUrlLink())
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

        return getCurrentUserPostsService.apply(showCurrentUserList());
    }

    @Override
    public PostCurrentUserResponse updatePostCurrentUserResponse(PostCurrentUserResponse postCurrentUserResponse) {

        final PostCurrentUserResponse userRequest = getCurrentUserSinglePostService.apply(postCurrentUserResponse.getCommentId());

        List<BadgeResponse> badgeList = separateBadge(postCurrentUserResponse.getBadgeAll());

        BadgeResponse badgeResponse = badgeList.get(0);

        final PostSaveModel postSaveModel = PostSaveModel.builder()
                .title(postCurrentUserResponse.getTitle().isEmpty() ? userRequest.getTitle() : postCurrentUserResponse.getTitle())
                .commentId(userRequest.getCommentId())
                .postStatusType(userRequest.getPostStatusType())
                .body((postCurrentUserResponse.getBody().isEmpty() && !postCurrentUserResponse.getBody().equals(userRequest.getBody()))
                        ? userRequest.getBody() : postCurrentUserResponse.getBody())
                .category((postCurrentUserResponse.getCategory().isEmpty() && !(postCurrentUserResponse.getCategory().equals(userRequest.getCategory())))
                        ? userRequest.getCategory() : postCurrentUserResponse.getCategory())
                .createDate(DateUtils.asDate(userRequest.getCreateDate()))
                .updatedDate(new Date())
                .userId(getUserIdService.getUserId())
                .urlLink((postCurrentUserResponse.getUrlLink().isEmpty() && !postCurrentUserResponse.getUrlLink().equals(userRequest.getUrlLink()))
                        ? userRequest.getUrlLink() : postCurrentUserResponse.getUrlLink())
                .badgeOne((!userRequest.getBadgeOne().equals(badgeResponse.getBadgeOne()) && badgeResponse.getBadgeOne().isEmpty())
                        ? userRequest.getBadgeOne() : badgeResponse.getBadgeOne())
                .badgeTwo((!userRequest.getBadgeTwo().equals(badgeResponse.getBadgeTwo()) && badgeResponse.getBadgeTwo().isEmpty())
                        ? userRequest.getBadgeTwo() : badgeResponse.getBadgeTwo())
                .badgeThree((!userRequest.getBadgeThree().equals(badgeResponse.getBadgeThree()) && badgeResponse.getBadgeThree().isEmpty())
                        ? userRequest.getBadgeThree() : badgeResponse.getBadgeThree())
                .badgeFour((!userRequest.getBadgeFour().equals(badgeResponse.getBadgeFour()) && badgeResponse.getBadgeFour().isEmpty())
                        ? userRequest.getBadgeFour() : badgeResponse.getBadgeFour())
                .badgeFive((!userRequest.getBadgeFive().equals(badgeResponse.getBadgeFive()) && badgeResponse.getBadgeFive().isEmpty())
                        ? userRequest.getBadgeFive() : badgeResponse.getBadgeFive())
                .imageUrlLink(userRequest.getImageUrlLink())
                .build();

        final PostCurrentUserResponse newUserResponse = updatePostService.apply(postSaveModel);

        return newUserResponse;
    }

    @Override
    public PostEntity showIdImage(String commentId) {
        final PostEntity imageUrlLink = postRepository.findByCommentId(commentId);
        return imageUrlLink;
    }
}
