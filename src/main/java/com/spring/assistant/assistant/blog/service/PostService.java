package com.spring.assistant.assistant.blog.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.request.PostRequestModel;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PostService {

    PostEntity saveNewPost(PostRequestModel postRequestModel, MultipartFile multipartFile);

    List<PostEntity> showCurrentUserList();

    List<PostCurrentUserResponse> postCurrentUserResponse();


}
