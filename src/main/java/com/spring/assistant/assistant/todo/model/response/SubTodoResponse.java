package com.spring.assistant.assistant.todo.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SubTodoResponse {


    private String taskId;

    private String subTodoTitle;

    private String subTodoDescription;

    private String subTodoCategory;

    private LocalDate subTodoCreatedDate;

    private LocalDate subTodoFinishDate;

    private String userId;

}
