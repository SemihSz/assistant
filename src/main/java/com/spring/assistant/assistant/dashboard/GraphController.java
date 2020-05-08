package com.spring.assistant.assistant.dashboard;

import com.spring.assistant.assistant.dashboard.model.CollectionAll;
import com.spring.assistant.assistant.dashboard.model.DashboardBlogModel;
import com.spring.assistant.assistant.dashboard.model.DashboardDeleteModel;
import com.spring.assistant.assistant.dashboard.model.DashboardModel;
import com.spring.assistant.assistant.dashboard.model.DashboardMovieModel;
import com.spring.assistant.assistant.dashboard.model.NotificationTodoModel;
import com.spring.assistant.assistant.dashboard.request.DashboardRequest;
import com.spring.assistant.assistant.dashboard.service.DashboardServiceInterface;
import com.spring.assistant.assistant.interfaces.service.GetUserIdService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author semih on Aralık, 2019, 22.12.2019, 20:42:28
 */
@Controller
@AllArgsConstructor
public class GraphController {


	private final DashboardServiceInterface dashboardService;

	private final GetUserIdService getUserIdService;

	private final RestTemplate restTemplate;

	@GetMapping("/displayBarGraph")
	public String barGraph(Model model) {
		return "barGraph.html";
	}

	@GetMapping("/get-delete-data-list")
	public ResponseEntity<Map<String, Integer>> getDeleteTodoSubTodoData() {
		return ResponseEntity.ok().body(dashboardService.showCategoryDeleteList(getUserIdService.getUserId()));
	}

	@GetMapping("/show-delete-count-dashboard")
	public ResponseEntity<DashboardDeleteModel> showDeleteCountDashboard() {
		return ResponseEntity.ok().body(dashboardService.showValueOfDeleteTodoSubTodo(getUserIdService.getUserId()));
	}

	@GetMapping("show-all-dashboard-parameter")
	public ResponseEntity<DashboardModel> showDashboard() {
		return ResponseEntity.ok().body(dashboardService.getDashboardData(getUserIdService.getUserId()));
	}

	@GetMapping("/show-blog-statistic")
	public ResponseEntity<DashboardBlogModel> showBlogModel() {
		return ResponseEntity.ok().body(dashboardService.getBlogData(getUserIdService.getUserId()));
	}

	@GetMapping("/get-todo-category")
	public ResponseEntity<Map<String, Integer>> showTodoCategory() {
		return ResponseEntity.ok().body(dashboardService.showTodoCategoryList(getUserIdService.getUserId()));
	}

	@GetMapping("/get-all-collection")
	public ResponseEntity<Collection<CollectionAll>> getAllCollection() {
		return ResponseEntity.ok().body(dashboardService.getAllCollection(getUserIdService.getUserId()));
	}

	//TODO PathVariable kullan!!
	@GetMapping("/show-user-movie")
	public ResponseEntity<DashboardMovieModel> getMovie(DashboardRequest dashboardRequest) {
		dashboardRequest.setUserInput(3);
		dashboardRequest.setUserId(getUserIdService.getUserId());
		return ResponseEntity.ok().body(dashboardService.getMovieData(dashboardRequest));
	}

	@GetMapping("/notification-todo")
	public ResponseEntity<List<NotificationTodoModel>> getNotification() {
		return ResponseEntity.ok().body(dashboardService.getNotifications(getUserIdService.getUserId()));
	}

	@GetMapping("/ddd")
	public ResponseEntity<String> getResrest() {
		return restTemplate.getForEntity("http://127.0.0.1:5000/python", String.class, getUserIdService.getUserId());

	}
}
