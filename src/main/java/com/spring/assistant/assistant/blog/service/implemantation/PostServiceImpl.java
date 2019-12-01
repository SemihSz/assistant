package com.spring.assistant.assistant.blog.service.implemantation;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.request.PostRequestModel;
import com.spring.assistant.assistant.blog.service.PostService;
import com.spring.assistant.assistant.todo.shared.enums.PostStatusType;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import com.spring.assistant.assistant.usercontroller.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

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

    @Override
    public PostEntity saveNewPost(PostRequestModel postRequestModel) {


        logger.info("Now Creating the new post");
        final PostEntity postEntity = PostEntity.builder()
                .title(postRequestModel.getTitle())
                .body(postRequestModel.getBody())
                .attachFile(postRequestModel.getAttachFile())
                .createDate(new Date())
                .updatedDate(null)
                .userId(getUserId())
                .commentId(createCommentId())
                .postStatusType(PostStatusType.NEW).build();
        logger.info("Comment id = {}, user id = {}", postEntity.getCommentId(), postEntity.getUserId());
        postRepository.save(postEntity);
        logger.info("Save post into DB!");
        return postEntity;
    }

    private String getUserId() {
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
