package com.spring.assistant.assistant.todo.controller;

import com.spring.assistant.assistant.todo.entity.InProgressEntity;
import com.spring.assistant.assistant.todo.service.executable.model.InProgressRequest;
import com.spring.assistant.assistant.todo.service.executable.service.InProgressSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author semih on Aralık, 2019, 20.12.2019, 00:09:54
 */
@Controller
public class deneme {

	@Autowired
	InProgressSaveService inProgressSaveService;

	@PostMapping(path = "/deneme")
	public String de(Model model, InProgressRequest inProgressRequest) {

		InProgressRequest inProgressModel = InProgressRequest.builder()
				.inProgressTaskOne(inProgressRequest.getInProgressTaskOne())
				.inProgressTaskTwo(inProgressRequest.getInProgressTaskTwo())
				.build();

		model.addAttribute("inProgressRequest", inProgressRequest);
		InProgressEntity inProgressEntity = inProgressSaveService.apply(inProgressModel);
		return "deneme";

	}

	@GetMapping(path = "/deneme")
	public String getCreatePostPage(InProgressRequest inProgressRequest, Model model) {
		model.addAttribute("inProgressRequest", inProgressRequest);
		return "deneme";
	}
}
