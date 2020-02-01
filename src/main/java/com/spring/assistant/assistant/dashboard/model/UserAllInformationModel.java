package com.spring.assistant.assistant.dashboard.model;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import com.spring.assistant.assistant.general.model.KeyAndValue;
import com.spring.assistant.assistant.movies.entity.UserMoviesEntity;
import com.spring.assistant.assistant.todo.entity.TodoEntity;
import com.spring.assistant.assistant.todo.service.executable.model.FinishTodoModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author semih on Ocak, 2020, 28.01.2020, 21:25:08
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAllInformationModel {

	private List<TodoEntity> todoEntityList;

	private List<PostEntity> postEntities;

	private List<UserMoviesEntity> userMoviesEntities;

	private List<FinishTodoModel> finishTodoModels;

	private List<KeyAndValue> keyAndValuesEmail;
}
