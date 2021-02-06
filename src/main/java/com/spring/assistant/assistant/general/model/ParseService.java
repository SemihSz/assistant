package com.spring.assistant.assistant.general.model;

import org.springframework.stereotype.Service;

/**
 * @author semih on Mart, 2020, 15.03.2020, 12:22:05
 */
@Service
public interface ParseService {

	Object parse(String url);
}
