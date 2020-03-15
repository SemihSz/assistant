package com.spring.assistant.assistant.dashboard.service;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.blog.repository.PostRepository;
import com.spring.assistant.assistant.blog.statistic.entity.StatisticEntity;
import com.spring.assistant.assistant.blog.statistic.repository.StatisticRepository;
import com.spring.assistant.assistant.dashboard.model.CategoryModel;
import com.spring.assistant.assistant.dashboard.model.CollectionAll;
import com.spring.assistant.assistant.dashboard.model.DashboardBlogModel;
import com.spring.assistant.assistant.dashboard.model.DashboardDeleteModel;
import com.spring.assistant.assistant.dashboard.model.DashboardModel;
import com.spring.assistant.assistant.dashboard.model.DashboardMovieModel;
import com.spring.assistant.assistant.dashboard.model.MovieCategoryResponse;
import com.spring.assistant.assistant.dashboard.model.MovieModel;
import com.spring.assistant.assistant.dashboard.model.NotificationTodoModel;
import com.spring.assistant.assistant.dashboard.model.UserAllInformationModel;
import com.spring.assistant.assistant.dashboard.request.DashboardRequest;
import com.spring.assistant.assistant.general.constant.AssistantConstant;
import com.spring.assistant.assistant.general.model.KeyAndValue;
import com.spring.assistant.assistant.movies.entity.UserMoviesEntity;
import com.spring.assistant.assistant.movies.repository.UserMoviesRepository;
import com.spring.assistant.assistant.todo.entity.DeleteAllTodoEntity;
import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.repository.DeleteAllTodoRepository;
import com.spring.assistant.assistant.todo.repository.SubTodoRepository;
import com.spring.assistant.assistant.todo.repository.TodoRepository;
import com.spring.assistant.assistant.todo.service.CategoryService;
import com.spring.assistant.assistant.todo.service.executable.model.TodoDateCompareModel;
import com.spring.assistant.assistant.todo.service.executable.service.FinishTodoService;
import com.spring.assistant.assistant.todo.service.executable.service.TodoDateCompareService;
import com.spring.assistant.assistant.todo.shared.enums.ImportantType;
import com.spring.assistant.assistant.todo.shared.enums.PrefixType;
import com.spring.assistant.assistant.todo.shared.utils.MapUtils;
import com.spring.assistant.assistant.todo.shared.utils.TextUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author semih on Ocak, 2020, 12.01.2020, 23:19:49
 */
//TODO dashboard service yap Statistic sevislerin yaptığı verileri her şeyi oluştur baya uzun sürecek diye tahmin ediliyor.

@Service
@Slf4j
@AllArgsConstructor
public class DashboardService implements DashboardServiceInterface {


	private final DeleteAllTodoRepository deleteAllTodoRepository;

	private final CategoryService categoryService;

	private final CollectionService collectionService;

	private final FinishTodoService finishTodoService;

	private final TodoDateCompareService dateCompareService;

	private final DashboardCalculateService dashboardCalculateService;

	private final TodoRepository todoRepository;

	private final SubTodoRepository subTodoRepository;

	private final PostRepository postRepository;

	private final UserMoviesRepository userMoviesRepository;

	private final StatisticRepository statisticRepository;

	private final MapUtils mapUtils;

	private final TextUtil textUtil;


	public DashboardDeleteModel showValueOfDeleteTodoSubTodo(String userId) {
		int countTodo = 0;

		int countSubTodo = 0;

		final List<DeleteAllTodoEntity> deleteList = deleteAllTodoRepository.findByUserId(userId);

		for (DeleteAllTodoEntity deleteAllTodoEntity : deleteList) {
			if (deleteAllTodoEntity.getTodoPrefix().equals(PrefixType.SUBTODO)) {
				countSubTodo++;
			} else {
				countTodo++;
			}
		}
		log.info("calculate showValueOfDeleteTodoSubTodo count To-do and sub-todo");

		final DashboardDeleteModel dashboardDeleteModel = DashboardDeleteModel.builder()
				.numberOfSubTodoDelete(countSubTodo)
				.numberOfTodoDelete(countTodo)
				.build();

		return dashboardDeleteModel;
	}

	public Map<String, Integer> showCategoryDeleteList(String userId) {

		List<String> result = categoryService.findAllCategoryListName();
		Map<String, Integer> getterCategoryInfo = new HashMap<>();

		final List<DeleteAllTodoEntity> deleteList = deleteAllTodoRepository.findByUserId(userId);
		for (String s : result) {
			for (DeleteAllTodoEntity deleteAllTodoEntity : deleteList) {

				if (s.equals(deleteAllTodoEntity.getCategory())) {
					getterCategoryInfo = mapUtils.incrementValue(getterCategoryInfo, s);
				}
			}
		}
		log.info("mapping showCategoryDeleteList delete category");
		return getterCategoryInfo;
	}

	public Map<String, Integer> showTodoCategoryList(String userId) {
		final List<String> result = categoryService.findAllCategoryListName();
		Map<String, Integer> getterCategoryInfo = new HashMap<>();
		final List<TodoEntity> todoEntityList = getterTodoList(userId);
		for (String s : result) {
			for (TodoEntity todoEntity : todoEntityList) {

				if (s.equals(todoEntity.getCategory())) {
					getterCategoryInfo = mapUtils.incrementValue(getterCategoryInfo, s);
				}
			}
		}
		log.info("mapping showCategoryDeleteList todo category");
		return getterCategoryInfo;
	}

	public DashboardModel getDashboardData(String userId) {

		log.info("get user specif all data from db getDashboardData");

		final List<TodoEntity> allTotalTodo = todoRepository.findAll();
		final List<TodoEntity> todoEntityList = getterTodoList(userId);
		final List<SubTodoEntity> subTodoEntities = subTodoRepository.findByUserId(userId);
		final List<PostEntity> postEntities = postRepository.findByUserId(userId);
		final List<UserMoviesEntity> moviesEntities = userMoviesRepository.findByUserId(userId);
		final List<TodoEntity> finishTodoList = todoRepository.showFinishList(userId);
		final List<TodoEntity> inProgressList = todoRepository.showInProgressList(userId);

		log.info("get user data is finished");

		log.info("local date comparator service");

		final List<TodoDateCompareModel> dateCompareModel = dateCompareService.apply(todoEntityList);

		final Map<String, Long> importantLevelCount = todoEntityList
				.stream()
				.filter(e -> !e.getImportantLevel().isEmpty())
				.collect(Collectors.groupingBy(e -> e.getImportantLevel(), Collectors.counting()));

		final Map<String, Long> convertCategory = new HashMap<>();
		convertCategory.put(ImportantType.FIVE.importantLevelNum, importantLevelCount.get("5"));
		convertCategory.put(ImportantType.FOUR.importantLevelNum, importantLevelCount.get("4"));
		convertCategory.put(ImportantType.THREE.importantLevelNum, importantLevelCount.get("3"));
		convertCategory.put(ImportantType.TWO.importantLevelNum, importantLevelCount.get("2"));
		convertCategory.put(ImportantType.ONE.importantLevelNum, importantLevelCount.get("1"));

		log.info("sort important level and group by");

		final UserAllInformationModel setUserAllInf = UserAllInformationModel.builder()
				.todoEntityList(todoEntityList)
				.postEntities(postEntities)
				.userMoviesEntities(moviesEntities)
				.finishTodoModels(finishTodoService.apply(finishTodoList))
				.build();

		log.info("UserAllInformationModel is finished ");

		final DashboardBlogModel dashboardBlogModel = getBlogData(userId);

		final DashboardModel dashboardModel = DashboardModel.builder()
				.allTodo(allTotalTodo.size())
				.numberOfTodo(todoEntityList.size())
				.numberOfSubTodo(subTodoEntities.size())
				.numberOfMovies(moviesEntities.size())
				.numberOfBlog(postEntities.size())
				.numberOfFinishTodo(finishTodoList.size())
				.numberOfProgressTodo(inProgressList.size())
				.importantLevelCount(convertCategory)
				.dashboardBlogModel(dashboardBlogModel)
				.setUserAllInf(setUserAllInf)
				.passExpectedDateSize(dateCompareModel)
				.build();

		log.info("DashboardModel is finished ");

		return dashboardModel;
	}

	public DashboardBlogModel getBlogData(String userId) {

		final Long yourBlogslength = statisticRepository.getPostLength(userId);

		final Collection<StatisticEntity> statisticEntities = statisticRepository.findByUserId(userId).stream().collect(Collectors.toList());

		final List<CategoryModel> categoryList = new ArrayList<>();

		log.info("now getBlogData method start to working");

		for (StatisticEntity statisticEntity : statisticEntities) {
			boolean controlInListIncluded = true;
			if (!categoryList.isEmpty()) {
				for (CategoryModel model : categoryList) {
					if (model.getCategoryName().equals(statisticEntity.getCategory())) {
						model.setCountOfCategory(model.getCountOfCategory() + 1);
						controlInListIncluded = false;
					}
				}
				if (controlInListIncluded) {
					final CategoryModel categoryModel = CategoryModel.builder()
							.categoryName(statisticEntity.getCategory())
							.countOfCategory(1)
							.build();
					categoryList.add(categoryModel);
				}
			} else {
				final CategoryModel categoryModel = CategoryModel.builder()
						.categoryName(statisticEntity.getCategory())
						.countOfCategory(1)
						.build();
				categoryList.add(categoryModel);
			}
		}

		final Map<String, Integer> convertMap = categoryList.stream()
				.collect(Collectors.toMap(CategoryModel::getCategoryName,
						CategoryModel::getCountOfCategory));

		final DashboardBlogModel dashboardBlogModel = DashboardBlogModel.builder()
				.allBlogLength(yourBlogslength)
				.blogCategory(convertMap)
				.build();

		log.info("getBlogData is finished");
		return dashboardBlogModel;

	}

	private Collection<CollectionAll> getCollectionAll(DashboardModel dashboardModel, UserAllInformationModel setUserAllInf) {

		List<KeyAndValue> keyAndValues = new ArrayList<>();

		for (TodoEntity todoEntity : setUserAllInf.getTodoEntityList()) {

			final KeyAndValue keyAndValue = KeyAndValue.builder()
					.key(todoEntity.getEmail())
					.build();

			keyAndValues.add(keyAndValue);
		}
		log.info("getCollectionAll method KeyAndValue is finished");

		setUserAllInf.setKeyAndValuesEmail(keyAndValues);

		log.info("setUserInfo add into mail");

		final Collection<CollectionAll> collect = collectionService.apply(dashboardModel, setUserAllInf);
		log.info("collection service is finished");
		return collect;
	}

	public Collection<CollectionAll> getAllCollection(String userId) {

		final DashboardModel dashboardModel = getDashboardData(userId);

		final Collection<CollectionAll> getAllCollection = getCollectionAll(dashboardModel, dashboardModel.getSetUserAllInf());

		return getAllCollection;

	}

	public DashboardMovieModel getMovieData(DashboardRequest dashboardRequest) {

		Set<String> movieCategory = new HashSet<>();
		List<String> movieCategoryList = new ArrayList<>();
		List<MovieModel> movieCategoryAverageScoreList = new ArrayList<>();

		final List<UserMoviesEntity> moviesEntities = userMoviesRepository.findByUserId(dashboardRequest.getUserId());

		if (moviesEntities.size() != 0) {
			for (UserMoviesEntity userMoviesEntity : moviesEntities) {
				if (!StringUtils.isEmpty(userMoviesEntity.getMovieCategory())) {
					final String[] newCategory = textUtil.separateString(userMoviesEntity.getMovieCategory());
					if (newCategory.length > 0) {
						movieCategory.addAll(Arrays.asList(newCategory));
						movieCategoryList.addAll(Arrays.asList(newCategory));

						for (String addListCount : newCategory) {

							final MovieModel movieModel = MovieModel.builder()
									.category(addListCount)
									.categoryAverageScore(userMoviesEntity.getGeneralScore())
									.build();

							movieCategoryAverageScoreList.add(movieModel);
						}
					}
				}
			}

			final Map<String, Integer> convertMap = moviesEntities.stream()
					.collect(Collectors.toMap(UserMoviesEntity::getMovieName,
							UserMoviesEntity::getNumberOfWatchTime));

			final Map<String, Integer> numberOfWatchMovieName = convertMap.entrySet()
					.stream()
					.limit(AssistantConstant.LIMIT)
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.collect(Collectors.toMap(
							Map.Entry::getKey,
							Map.Entry::getValue,
							(o1, o2) -> o1,
							LinkedHashMap::new));

			final Map<String, Long> movieCategoryCount = movieCategoryList
					.stream()
					.collect(Collectors.groupingBy(Function.identity(),
							Collectors.counting()));

			final MovieCategoryResponse movieCategoryResponse = dashboardCalculateService.apply(movieCategoryAverageScoreList,
					dashboardRequest.getUserInput());

			final Map<String, Date> dateAndMovieName = moviesEntities.stream().collect(Collectors.toMap(UserMoviesEntity::getMovieName,
					UserMoviesEntity::getLastWatchDate));

			final DashboardMovieModel dashboardMovieModel = DashboardMovieModel.builder()
					.movieCategory(movieCategory)
					.countMovieCategory(movieCategoryCount)
					.movieCategoryResponse(movieCategoryResponse)
					.numberOfMoviesCount(numberOfWatchMovieName)
					.dateAndMovie(dateAndMovieName)
					.maxCategoryCount(movieCategoryCount.entrySet()
							.stream()
							.max((entry1, entry2) -> entry1.getValue()
									> entry2.getValue() ? 1 : -1).get().getValue())
					.build();

			return dashboardMovieModel;
		}

		return null;
	}

	@Override
	public List<NotificationTodoModel> getNotifications(String userId) {

		final List<TodoEntity> todoList = getterTodoList(userId);
		List<NotificationTodoModel> notificationTodoModelList = new ArrayList<>();
		for (TodoEntity todoEntity : todoList) {
			if (todoEntity.getImportantLevel().equals(AssistantConstant.IMPORTANT_LEVEL_MAX)) {
				final NotificationTodoModel notificationTodoModel = NotificationTodoModel.builder()
						.title(todoEntity.getTitle())
						.date(todoEntity.getExpectFinishDate())
						.description(todoEntity.getDescription())
						.build();

				notificationTodoModelList.add(notificationTodoModel);
			}
		}
		return notificationTodoModelList;
	}

	private List<TodoEntity> getterTodoList(String userId) {
		return todoRepository.finds(userId);
	}

}
