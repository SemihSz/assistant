package com.spring.assistant.assistant.todo.service.executable.service;

import com.spring.assistant.assistant.interfaces.SimpleTask;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.service.executable.model.TodoDateCompareModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author semih on Şubat, 2020, 1.02.2020, 23:38:01
 */
@Service
@Slf4j
public class TodoDateCompareService implements SimpleTask<List<TodoEntity>, List<TodoDateCompareModel>> {


	@Override
	public List<TodoDateCompareModel> apply(List<TodoEntity> todoEntities) {
		return comparatorService(todoEntities);
	}

	private List<TodoDateCompareModel> comparatorService(List<TodoEntity> todoEntities) {
		List<TodoDateCompareModel> todoDateCompareModels = new ArrayList<>();
		List<TodoEntity> passList = new ArrayList<>();
		List<TodoEntity> notPassList = new ArrayList<>();
		List<TodoEntity> equalList = new ArrayList<>();

		int passExpectedDate = 0;
		int notPassExpectedDate = 0;
		int isEqualDay = 0;
		for (TodoEntity todoEntity : todoEntities) {
			if (compareDate(LocalDate.now(), todoEntity.getExpectFinishDate()) < 0) {
				passExpectedDate++;
				passList.add(todoEntity);
			} else if (compareDate(LocalDate.now(), todoEntity.getExpectFinishDate()) == 0) {
				isEqualDay++;
				equalList.add(todoEntity);
			} else {
				notPassExpectedDate++;
				notPassList.add(todoEntity);
			}
		}

		final TodoDateCompareModel todoDateCompareModel = TodoDateCompareModel.builder()
				.passExpectedDate(passExpectedDate)
				.notPassExpectedDate(notPassExpectedDate)
				.isEqual(isEqualDay)
				.passList(passList)
				.notPassList(notPassList)
				.equalList(equalList)
				.build();

		todoDateCompareModels.add(todoDateCompareModel);

		log.info("TodoDateCompareService is working correctly");

		return todoDateCompareModels;
	}

	private Integer compareDate(LocalDate now, LocalDate end) {

		return end.compareTo(now);
	}
}
