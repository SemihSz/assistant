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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public String createNewPost(PostRequestModel requestModel, @RequestParam("file") MultipartFile multipartFile) {
        PostEntity postEntity = postService.saveNewPost(requestModel, multipartFile);
        return "redirect:/blog/get-different-user-list";
    }

    @GetMapping(path = "/get-current-user-list")
    public ResponseEntity<List<PostEntity>> showCurrentUserList() {

        return ResponseEntity.ok().body(postService.showCurrentUserList());
    }

    @GetMapping(path = "get-different-user-list")
    public String getList(Model model) {
        model.addAttribute("posts", postService.postCurrentUserResponse());
        return "blog/show-blogs";
    }


}
