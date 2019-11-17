package com.spring.assistant.assistant.todo.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoTaskIdRequestModel extends TodoRequestModel {

    private String taskId;

    private Boolean isFinnished;

}
