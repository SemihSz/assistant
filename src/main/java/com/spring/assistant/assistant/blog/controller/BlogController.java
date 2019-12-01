package com.spring.assistant.assistant.blog.controller;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.request.PostRequestModel;
import com.spring.assistant.assistant.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    PostService postService;

    @GetMapping(path = "/home")
    public ResponseEntity blogHomePage() {
        return ResponseEntity.ok().body("OKEY");
    }

    @GetMapping(path = "/create-blog")
    public String getCreatePostPage(PostRequestModel postRequestModel, Model model) {
        model.addAttribute("postRequestModel", postRequestModel);
        return "blog/create-blog";
    }


    @PostMapping(path = "/create-post")
    private ResponseEntity<PostEntity> createNewPost(PostRequestModel requestModel) {
        PostEntity postEntity = postService.saveNewPost(requestModel);
        return ResponseEntity.ok().body(postEntity);
    }


}
