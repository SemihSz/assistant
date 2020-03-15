package com.spring.assistant.assistant.todo.shared.utils;

import com.spring.assistant.assistant.blog.model.BodyMap;
import com.spring.assistant.assistant.blog.model.PostBodyModel;
import com.spring.assistant.assistant.todo.shared.SortByRoll;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author semih on Aralık, 2019, 14.12.2019, 16:30:39
 */

@NoArgsConstructor
@Component
public class TextUtil {


	public List<BodyMap> addingIntoList(PostBodyModel postBodyModel) {

		String[] arr = postBodyModel.getPostBody().toLowerCase().split(" ");


		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		List<BodyMap> bodyMaps = new ArrayList<>();

		for (String word : arr) {

			String wordInput = word.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

			BodyMap bodyMap = new BodyMap();

			if (wordMap.get(wordInput) != null) {

				Integer count = wordMap.get(wordInput) + 1;
				wordMap.put(wordInput, count);

				for (BodyMap bodyMap1 : bodyMaps) {
					if (bodyMap1.getName().equals(wordInput)) {
						bodyMap1.setCount(count);
						break;
					}
				}
			} else {
				Integer count = 1;
				wordMap.put(wordInput, count);
				bodyMap.setCount(count);
				bodyMaps.add(new BodyMap(wordInput, count));
			}

		}
		return bodyMaps;
	}

	public String getMostCommonWords(PostBodyModel postBodyModel) {

		final List<BodyMap> list = addingIntoList(postBodyModel);

		final List<String> mostCommonWordslList = new ArrayList<>();

		Collections.sort(list, new SortByRoll());

		for (int i = 1; i < list.size(); i++) {

			if (list.size() > (list.size() / 3)) {
				if (i == 25) {
					break;
				}
			}
			if (list.size() >= 4 && list.size() < 20) {

				if (i == 4)
					break;

				else {
					if (i == 3)
						break;
				}
			}


			mostCommonWordslList.add(list.get(list.size() - i).getName());

		}

		return convertListToString(mostCommonWordslList).toString();

	}

	public StringBuilder convertListToString(List<String> list) {

		StringBuilder newString = new StringBuilder();
		for (String s : list) {
			newString.append(s).append(" ");
		}

		return newString;

	}

	public String[] separateString(String s) {
		String a = s.replaceAll("\\s+", "");
		String[] neList = a.split(",");
		return neList;
	}


}
