package com.spring.assistant.assistant.dashboard.service;

import com.spring.assistant.assistant.dashboard.model.CollectionAll;
import com.spring.assistant.assistant.dashboard.model.DashboardModel;
import com.spring.assistant.assistant.dashboard.model.UserAllInformationModel;
import com.spring.assistant.assistant.general.model.KeyAndValue;
import com.spring.assistant.assistant.interfaces.Mapper;
import com.spring.assistant.assistant.todo.shared.enums.EmailType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author semih on Ocak, 2020, 28.01.2020, 21:38:20
 */
@Service
@AllArgsConstructor
@Slf4j
public class CollectionService implements Mapper<DashboardModel, UserAllInformationModel, Collection<CollectionAll>> {

	@Override
	public Collection<CollectionAll> apply(DashboardModel dashboardModel, UserAllInformationModel userAllInformationModel) {
		return buildCollectionService(dashboardModel, userAllInformationModel);
	}

	private Collection<CollectionAll> buildCollectionService(DashboardModel dashboardModel, UserAllInformationModel userAllInformationModel) {

		final Map<String, List<KeyAndValue>> emailResultMap = userAllInformationModel.getKeyAndValuesEmail()
				.stream()
				.collect(Collectors.groupingBy(KeyAndValue::getKey));

		final Collection<CollectionAll> collectionAll = Collections.singleton(CollectionAll.builder()
				.allUserInformationCollections(userAllInformationModel)
				.dashboardModel(dashboardModel)
				.dashboardBlogModel(dashboardModel.getDashboardBlogModel())
				.emailResultMap(emailResultMap)
				.getEmailTypeAndSize(getEmailTypeAndSize(emailResultMap))
				.build());

		return collectionAll;
	}

	private Map<String, Double> getEmailTypeAndSize(Map<String, List<KeyAndValue>> emailResultMap) {

		Map<String, Double> getSizeOfMap = new HashMap<>();

		final int size = emailResultMap.get(EmailType.NOTSEND.getEmailType()).size()
				+ emailResultMap.get(EmailType.NEW.getEmailType()).size()
				+ emailResultMap.get(EmailType.SEND.getEmailType()).size();

		getSizeOfMap.put(EmailType.NOTSEND.getEmailType(), getPercentage(emailResultMap.get(EmailType.NOTSEND.getEmailType()).size(), size));
		getSizeOfMap.put(EmailType.NEW.getEmailType(), getPercentage(emailResultMap.get(EmailType.NEW.getEmailType()).size(), size));
		getSizeOfMap.put(EmailType.SEND.getEmailType(), getPercentage(emailResultMap.get(EmailType.SEND.getEmailType()).size(), size));

		return getSizeOfMap;
	}

	private Double getPercentage(int value, int totalSize) {
		return ((double) value / (double) totalSize) * 100.0;
	}

}
