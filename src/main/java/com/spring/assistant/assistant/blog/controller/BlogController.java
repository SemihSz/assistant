package com.spring.assistant.assistant.blog.controller;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.request.PostRequestModel;
import com.spring.assistant.assistant.blog.response.PostCurrentUserResponse;
import com.spring.assistant.assistant.blog.service.PostService;
import com.spring.assistant.assistant.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/blog")
@AllArgsConstructor
@Slf4j
public class BlogController {


	//TODO Resim işini yap
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	private final PostService postService;

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

	@PostMapping(path = "/update-post/{commentId}")
	public String updateCurrentPost(@PathVariable("commentId") String s, PostCurrentUserResponse postCurrentUserResponse, ResourceNotFoundException e, HttpServletRequest request) throws ResourceNotFoundException {
		postCurrentUserResponse.setCommentId(s);
		postService.updatePostCurrentUserResponse(postCurrentUserResponse);
		return "redirect:/blog/get-different-user-list";

	}

	/*@ExceptionHandler({ResourceNotFoundException.class, Exception.class})
	public ModelAndView handleError(HttpServletRequest req, Model model, ResourceNotFoundException exception)
			throws ResourceNotFoundException {

		// Rethrow annotated exceptions or they will be processed here instead.
		if (AnnotationUtils.findAnnotation(exception.getClass(),
				ResponseStatus.class) != null)
			throw exception;

		logger.error("Request: " + req.getRequestURI() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("message", exception.getMessage());
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);
		mav.setViewName("error");
		return mav;
	}*/

	@GetMapping(path = "/get-current-user-list")
	public ResponseEntity<List<PostEntity>> showCurrentUserList() {

		return ResponseEntity.ok().body(postService.showCurrentUserList());
	}

	@GetMapping(path = "/get-different-user-list")
	public String getList(PostRequestModel postRequestModel, Model model, HttpServletRequest request) {
		request.getParameter("body");
		model.addAttribute("posts", postService.postCurrentUserResponse());
		model.addAttribute("postRequestModel", postRequestModel);
		return "blog/show-blogs";
	}

	@GetMapping(path = "/get-different-user-lists")
	public ResponseEntity<List<PostCurrentUserResponse>> rgetList(PostRequestModel postRequestModel, Model model, HttpServletRequest request) {
		request.getParameter("body");
		model.addAttribute("posts", postService.postCurrentUserResponse());
		model.addAttribute("postRequestModel", postRequestModel);
		return ResponseEntity.ok().body(postService.postCurrentUserResponse());
	}

	@GetMapping(path = "/rest-api/{commentId}")
	public ResponseEntity<PostEntity> getList(@PathVariable String commentId, @RequestHeader HttpHeaders httpHeaders) {
		httpHeaders.add("comment", commentId);
		return ResponseEntity.ok().body(postService.showIdImage(commentId));
	}

	//TODO Delete Blog yap db'ye kayıt  dashboard göster


}
