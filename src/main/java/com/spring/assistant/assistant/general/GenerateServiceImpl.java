package com.spring.assistant.assistant.general;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.todo.shared.utils.GenerateNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
public class GenerateServiceImpl implements Serializable, GenerateService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateServiceImpl.class);

    private final int TASK_RANDOM_NUMBER = 15;

    @Autowired
    GenerateNumberUtil generateNumberUtil;

    @Autowired
    private PostRepository postRepository;


    @Override
    public String createCommentId() {
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

    @Override
    public String generateRandomTaskId() {

        logger.info("!!!Now Generate Random Task Id!!!");
        return (generateNumberUtil.generateUserId(TASK_RANDOM_NUMBER));
    }
}
