package com.spring.assistant.assistant.blog.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.request.PostRequestModel;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    PostEntity saveNewPost(PostRequestModel postRequestModel);

}
