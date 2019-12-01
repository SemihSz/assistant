package com.spring.assistant.assistant.blog.service.implemantation;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.request.PostRequestModel;
import com.spring.assistant.assistant.blog.response.BadgeResponse;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import com.spring.assistant.assistant.blog.service.PostService;
import com.spring.assistant.assistant.blog.service.UploadService;
import com.spring.assistant.assistant.exception.MaximumSizeException;
import com.spring.assistant.assistant.exception.ResourceNotFoundException;
import com.spring.assistant.assistant.todo.shared.enums.BadgeIndexType;
import com.spring.assistant.assistant.todo.shared.enums.PostStatusType;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import com.spring.assistant.assistant.usercontroller.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("post")
@Slf4j
public class PostServiceImpl implements PostService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);


    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GenerateNumberUtil generateNumberUtil;

    @Autowired
    private UploadService uploadService;

    @Override
    public PostEntity saveNewPost(PostRequestModel postRequestModel, MultipartFile multipartFile) {

        List<BadgeResponse> badgeList = separateBadge(postRequestModel.getBadgeAll());
        BadgeResponse badgeResponse = badgeList.get(0);
        logger.info("Now Creating the new post");

        String commentId = createCommentId();

        final PostEntity postEntity = PostEntity.builder()
                .title(postRequestModel.getTitle())
                .body(postRequestModel.getBody())
                .category(postRequestModel.getCategory())
                .createDate(new Date())
                .updatedDate(null)
                .userId(getUserId())
                .commentId(commentId)
                .postStatusType(PostStatusType.NEW)
                .badgeOne(badgeResponse.getBadgeOne())
                .badgeTwo(badgeResponse.getBadgeTwo())
                .badgeThree(badgeResponse.getBadgeThree())
                .badgeFour(badgeResponse.getBadgeFour())
                .badgeFive(badgeResponse.getBadgeFive())
                .build();


        if (!multipartFile.isEmpty()) {

            logger.info("Now Starting the upload service!");
            uploadService.storeFile(multipartFile, commentId);
            logger.info("Now uploading image into db Comment id = {}, user id = {}", postEntity.getCommentId(), postEntity.getUserId());

        }

        logger.info("Comment id = {}, user id = {}", postEntity.getCommentId(), postEntity.getUserId());

        postRepository.save(postEntity);

        logger.info("Save post into DB!");

        return postEntity;
    }

    private List<BadgeResponse> separateBadge(String badgeAll) {

        List<BadgeResponse> responses = new ArrayList<>();

        if (badgeAll == null) {

            logger.error("!! Badge is null. This process stop before separate process start!! badge={}", badgeAll);
            throw new ResourceNotFoundException("Badge is null. This process stop before separate process start");

        }

        String[] arr = badgeAll.split(" ");

        if (arr.length > 5) {

            logger.error("!! Badge maximum capacity is 5. This process stop before separate process start!! arrayList lenght={}", arr.length);
            throw new MaximumSizeException("Reach the max. of badge size. You have to remove some bage!");

        }

        List<String> list = new ArrayList<>();

        try {
            for (int i = 1; i <= 5; i++) {
                if (arr.length >= i) {
                    list.add(arr[i - 1].substring(0, 1).toUpperCase() + arr[i - 1].substring(1).toLowerCase());
                }
                if (arr.length == i) {
                    for (int j = 1; j <= (5 - arr.length); j++) {
                        list.add("");
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        BadgeResponse badgeResponse = BadgeResponse.builder()
                .badgeOne(list.get(BadgeIndexType.ZERO.getCode()))
                .badgeTwo(list.get(BadgeIndexType.ONE.getCode()))
                .badgeThree(list.get(BadgeIndexType.TWO.getCode()))
                .badgeFour(list.get(BadgeIndexType.THREE.getCode()))
                .badgeFive(list.get(BadgeIndexType.FOUR.getCode()))
                .build();

        responses.add(badgeResponse);

        return responses;

    }

    @Override
    public List<PostEntity> showCurrentUserList() {

        return postRepository.findByUserId(getUserId());

    }

    @Override
    public List<PostCurrentUserResponse> postCurrentUserResponse() {

        List<PostEntity> getCurrentPosts = postRepository.findByUserId(getUserId());
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
                    .build();

            newResponse.add(postCurrentUserResponse);
        }

        return newResponse;

    }


    public String getUserId() {
        logger.info("Taking the user id");
        final String username = showEmailAddress();
        return userService.findByEmail(username).getUserId();

    }

    private String showEmailAddress() {

        return userService.giveUserAuthenticationInformation();

    }

    private String createCommentId() {
        logger.info("Controlling the comment id");
        boolean controller = true;
        String commentId = null;
        while (controller) {
            commentId = generateNumberUtil.generateCommentId();
            PostEntity controllComentId = postRepository.findByCommentId(commentId);
            if (controllComentId != null) {
                logger.info("This comment id  has been created before = {}", commentId);
                commentId = generateNumberUtil.generateCommentId();
                logger.info("Create New Comment id = {}", commentId);

            } else {
                logger.info("Create New Comment id = {}", commentId);
                controller = false;
            }
        }
        return commentId;
    }
}
