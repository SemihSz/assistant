package com.spring.assistant.assistant.todo.service;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import com.spring.assistant.assistant.todo.model.request.SubTaskIdRequestModel;
import com.spring.assistant.assistant.todo.model.request.SubTodoRequestModel;
import com.spring.assistant.assistant.todo.shared.SubTodoDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("subtodo")
public interface SubTodoService {

    SubTodoDto createNewSubTodo(SubTodoRequestModel subTodoRequestModel);
    Boolean controlTitleDescription(String title, String desc);
    Boolean controlTitleDescriptionLenght(String title, String desc);
    Boolean controlTheStartDateAndFinsih(LocalDate start, LocalDate end);
    List<SubTodoEntity> showAllSubTaskForCurrentTaskId(String taskId);
    List<SubTodoEntity> showAllsSubTask();
    SubTodoDto updateTheSubTask(SubTodoRequestModel subTodoRequestModel);
    void finishSpecificTask(SubTaskIdRequestModel subTaskIdRequestModel);
    void deleteSpecificTodo(SubTaskIdRequestModel subTaskIdRequestModel);

}
