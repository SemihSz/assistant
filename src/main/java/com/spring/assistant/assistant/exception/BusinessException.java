package com.spring.assistant.assistant.exception;

/**
 * @author semih on Aralık, 2019, 19.12.2019, 23:45:52
 */
public class BusinessException extends RuntimeException {

	public BusinessException(String message) {

		super(message);

	}

	public BusinessException(String message, Throwable cause) {

		super(message, cause);

	}
}
