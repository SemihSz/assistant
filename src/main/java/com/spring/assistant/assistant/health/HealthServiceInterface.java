package com.spring.assistant.assistant.health;

import com.spring.assistant.assistant.health.model.Information;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author semih on Mart, 2020, 15.03.2020, 12:16:42
 */
@Service
public interface HealthServiceInterface {


	Map<String, List<Information>> getData(String url);

	Map<String, List<Information>> getSelectedCountryInformation(Map<String, List<Information>> data);

}
