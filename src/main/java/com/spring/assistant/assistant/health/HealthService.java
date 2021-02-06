package com.spring.assistant.assistant.health;

import com.spring.assistant.assistant.general.model.ParseService;
import com.spring.assistant.assistant.health.model.Information;
import com.spring.assistant.assistant.todo.shared.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author semih on Mart, 2020, 15.03.2020, 12:16:19
 */
@Service
@Slf4j
@AllArgsConstructor
public class HealthService implements HealthServiceInterface {

	private ParseService parseService;

	private Map<String, List<Information>> takeData;

	@Override
	public Map<String, List<Information>> getData(String url) {
		takeData = (Map<String, List<Information>>) parseService.parse(url);
		return getSelectedCountryInformation(takeData);
	}

	public Map<String, List<Information>> getSelectedCountryInformation(Map<String, List<Information>> data) {
		Map<String, List<Information>> stringListMap = new HashMap<>();
		data = takeData;
		for (String countryName : getCountryList(data)) {
			List<Information> specificCountryData = data.entrySet()
					.stream()
					.filter(e -> e.getKey().equals(countryName))
					.map(Map.Entry::getValue)
					.flatMap(List::stream)
					.collect(Collectors.toList());
			List<Information> countryListData = new ArrayList<>();
			if (specificCountryData.size() > 0) {
				for (int i = 0; i < specificCountryData.size(); i++) {
					Map<String, Object> getCurrentMap = (Map<String, Object>) specificCountryData.get(i);
					final Information informationDataModel = Information.builder()
							.date(DateUtils.stringToDate(getCurrentMap.get("date").toString()))
							.deaths(((Integer) getCurrentMap.get("deaths")).longValue())
							.confirmed(((Integer) getCurrentMap.get("confirmed")).longValue())
							.recovered(((Integer) getCurrentMap.get("recovered")).longValue()).build();

					countryListData.add(informationDataModel);
				}
			}

			stringListMap.put(countryName, countryListData);
		}
		System.out.println(stringListMap.size());
		return stringListMap;
		/*long maxConfirmed = 0;
		Long maxDeath = Long.valueOf(0);
		Long maxRecovery = Long.valueOf(0);
		Date confirmedDate = null;
		for (int index = 0; index<specificCountryData.size(); index++) {
			if (index == 0) {
				maxConfirmed = specificCountryData.get(index).getConfirmed();
				confirmedDate = specificCountryData.get(index).getDate();
			}
			else if (maxConfirmed < specificCountryData.get(index).getConfirmed() - specificCountryData.get(index-1).getConfirmed()) {
				maxConfirmed = specificCountryData.get(index).getConfirmed();
				confirmedDate = specificCountryData.get(index).getDate();
			}
		}*/

	}

	public String[] getCountryList(Map<String, List<Information>> data) {
		return data.keySet().toArray(new String[data.size()]);
	}

}
