package com.spring.assistant.assistant.blog.controller;

import com.spring.assistant.assistant.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author semih on Aralık, 2019, 15.12.2019, 01:14:34
 */
@ControllerAdvice
public class ServiceErrorAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ResourceNotFoundException.class})
	public void handleRunTimeException(RuntimeException e) {
	}

}
