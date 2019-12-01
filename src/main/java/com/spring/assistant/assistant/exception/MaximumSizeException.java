package com.spring.assistant.assistant.exception;

public class MaximumSizeException extends RuntimeException {

    public MaximumSizeException(String message) {

        super(message);

    }

    public MaximumSizeException(String message, Throwable cause) {

        super(message, cause);

    }
}
