package com.spring.assistant.assistant.general;

import com.spring.assistant.assistant.general.model.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author semih on Mart, 2020, 15.03.2020, 12:21:54
 */
@Service
public class JsonParsingService implements ParseService {


	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Object parse(String url) {
		return restTemplate.getForObject(url, Object.class);
	}
}
