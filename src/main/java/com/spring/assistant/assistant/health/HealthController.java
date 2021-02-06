package com.spring.assistant.assistant.health;

import com.spring.assistant.assistant.health.model.Information;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author semih on Mart, 2020, 15.03.2020, 12:16:01
 */
@AllArgsConstructor
@RestController
public class HealthController {


	private static final String JSON_URL = "https://pomber.github.io/covid19/timeseries.json";

	private HealthServiceInterface service;

	@GetMapping("/health")
	ResponseEntity<Map<String, List<Information>>> handle() {

		return ResponseEntity.ok().body(service.getData(JSON_URL));
	}
}
