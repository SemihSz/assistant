package com.spring.assistant.assistant.todo.shared;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

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

    public SubTodoDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getSubTodoUpdateDate() {
        return subTodoUpdateDate;
    }

    public void setSubTodoUpdateDate(LocalDate subTodoUpdateDate) {
        this.subTodoUpdateDate = subTodoUpdateDate;
    }

    public boolean getFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
