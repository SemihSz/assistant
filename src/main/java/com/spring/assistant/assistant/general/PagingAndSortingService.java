package com.spring.assistant.assistant.general;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author semih on Aralık, 2019, 15.12.2019, 21:31:16
 */
@Service
public class PagingAndSortingService {

	public String[] setSortInfoArray(Page<?> page) {
		final String[] sortInfoArray = page.getSort().toString().split(":");
		return Arrays.stream(sortInfoArray).map(String::trim).toArray(String[]::new);
	}

	public String getSortClassName(final String[] sortInfoArray, final String propertyName) {
		if (sortInfoArray[0].equals(propertyName)) {
			return "sorted" + sortInfoArray[1];
		}
		return "notSorted";
	}

	/**
	 * @param filterParameters: "page"->0, "size"->20, "sort"->employee,DESC
	 * @return &sort=employee,DESC
	 */
	public String buildMultiParamPartUrl(final HashMap<String, Object> filterParameters) {
		StringBuilder sb = new StringBuilder(0);

		for (Map.Entry<String, Object> entry : filterParameters.entrySet()) {
			if (entry.getValue() != null) {
				if (!entry.getKey().equals("page") && !entry.getKey().equals("size")) {
					final String[] value = (String[]) entry.getValue();
					sb.append("&").append(entry.getKey()).append("=").append(value[0]);
				}
			}
		}
		return sb.toString();
	}

}
