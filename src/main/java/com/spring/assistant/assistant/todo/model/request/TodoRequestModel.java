package com.spring.assistant.assistant.todo.model.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
public class TodoRequestModel {

    private long id;
    @NotNull
    @Size(min = 5)
    private String title;

    private String description;

    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private String importantLevel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectFinishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;

    private String taskId;

    private String userId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
