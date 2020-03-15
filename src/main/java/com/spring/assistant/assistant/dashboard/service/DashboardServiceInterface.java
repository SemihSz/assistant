package com.spring.assistant.assistant.dashboard.service;

import com.spring.assistant.assistant.dashboard.model.CollectionAll;
import com.spring.assistant.assistant.dashboard.model.DashboardBlogModel;
import com.spring.assistant.assistant.dashboard.model.DashboardDeleteModel;
import com.spring.assistant.assistant.dashboard.model.DashboardModel;
import com.spring.assistant.assistant.dashboard.model.DashboardMovieModel;
import com.spring.assistant.assistant.dashboard.model.NotificationTodoModel;
import com.spring.assistant.assistant.dashboard.request.DashboardRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author semih on Şubat, 2020, 8.02.2020, 23:21:43
 */
@Service
public interface DashboardServiceInterface {

	DashboardDeleteModel showValueOfDeleteTodoSubTodo(String userId);

	Map<String, Integer> showCategoryDeleteList(String userId);

	Map<String, Integer> showTodoCategoryList(String userId);

	DashboardModel getDashboardData(String userId);

	DashboardBlogModel getBlogData(String userId);

	Collection<CollectionAll> getAllCollection(String userId);

	DashboardMovieModel getMovieData(DashboardRequest dashboardRequest);

	List<NotificationTodoModel> getNotifications(String userId);

}
