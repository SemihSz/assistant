package com.spring.assistant.assistant.todo.model.response;

import java.time.LocalDate;
import java.util.Date;

public class SubTodoResponse {


    private String taskId;
    private String subTodoTitle;
    private String subTodoDescription;
    private String subTodoCategory;
    private LocalDate subTodoCreatedDate;
    private LocalDate subTodoFinishDate;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SubTodoResponse() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSubTodoTitle() {
        return subTodoTitle;
    }

    public void setSubTodoTitle(String subTodoTitle) {
        this.subTodoTitle = subTodoTitle;
    }

    public String getSubTodoDescription() {
        return subTodoDescription;
    }

    public void setSubTodoDescription(String subTodoDescription) {
        this.subTodoDescription = subTodoDescription;
    }

    public String getSubTodoCategory() {
        return subTodoCategory;
    }

    public void setSubTodoCategory(String subTodoCategory) {
        this.subTodoCategory = subTodoCategory;
    }

    public LocalDate getSubTodoCreatedDate() {
        return subTodoCreatedDate;
    }

    public void setSubTodoCreatedDate(LocalDate subTodoCreatedDate) {
        this.subTodoCreatedDate = subTodoCreatedDate;
    }

    public LocalDate getSubTodoFinishDate() {
        return subTodoFinishDate;
    }

    public void setSubTodoFinishDate(LocalDate subTodoFinishDate) {
        this.subTodoFinishDate = subTodoFinishDate;
    }
}
