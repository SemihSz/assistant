package com.spring.assistant.assistant.todo.model.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

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

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
