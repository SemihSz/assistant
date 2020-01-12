package com.spring.assistant.assistant.general;

import org.springframework.stereotype.Service;

@Service
public interface GenerateService {

    String createCommentId();

    String generateRandomTaskId();

}
