package com.spring.assistant.assistant.todo.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SubTodoDto {

    private long id;

    private String taskId;

    private String subTodoTitle;

    private String subTodoDescription;

    private String subTodoCategory;

    private LocalDate subTodoCreatedDate;

    private LocalDate subTodoFinishDate;

    private LocalDate subTodoUpdateDate;

    private boolean isFinished = false;

    private String subTaskId;

    private String userId;


}
