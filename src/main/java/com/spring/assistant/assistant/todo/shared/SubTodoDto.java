package com.spring.assistant.assistant.todo.shared;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SubTodoDto {

    private long id;
    private String taskId;
    private String subTodoTitle;
    private String subTodoDescription;
    private String subTodoCategory;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    private Date subTodoCreatedDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    private Date subTodoFinishDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
    private Date subTodoUpdateDate;
    private Boolean isFinished = false;
    private String subTaskId;

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

    public Date getSubTodoCreatedDate() {
        return subTodoCreatedDate;
    }

    public void setSubTodoCreatedDate(Date subTodoCreatedDate) {
        this.subTodoCreatedDate = subTodoCreatedDate;
    }

    public Date getSubTodoFinishDate() {
        return subTodoFinishDate;
    }

    public void setSubTodoFinishDate(Date subTodoFinishDate) {
        this.subTodoFinishDate = subTodoFinishDate;
    }

    public Date getSubTodoUpdateDate() {
        return subTodoUpdateDate;
    }

    public void setSubTodoUpdateDate(Date subTodoUpdateDate) {
        this.subTodoUpdateDate = subTodoUpdateDate;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
    }
}
