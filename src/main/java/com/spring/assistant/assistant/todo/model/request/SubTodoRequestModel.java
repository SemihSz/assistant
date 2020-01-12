package com.spring.assistant.assistant.todo.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@Setter
public class SubTodoRequestModel {


    private String taskId;

    private String subTodoTitle;

    private String subTodoDescription;

    private String subTodoCategory;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate subTodoCreatedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate subTodoFinishDate;

    private String userId;

    private String subTaskId;
}
