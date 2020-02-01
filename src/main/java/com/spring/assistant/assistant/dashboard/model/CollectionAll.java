package com.spring.assistant.assistant.dashboard.model;

import com.spring.assistant.assistant.general.model.KeyAndValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author semih on Ocak, 2020, 28.01.2020, 21:36:26
 */

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CollectionAll {

	private UserAllInformationModel allUserInformationCollections;

	private DashboardModel dashboardModel;

	private DashboardBlogModel dashboardBlogModel;

	private Map<String, List<KeyAndValue>> emailResultMap;

	private Map<String, Double> getEmailTypeAndSize;

}
