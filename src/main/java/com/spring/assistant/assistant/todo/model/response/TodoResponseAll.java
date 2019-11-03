package com.spring.assistant.assistant.todo.model.response;

import java.util.Date;

public class TodoResponseAll {

    private long id;
    private String title;
    private String description;
    private String category;
    private String userId;
    private Date createdDate;
    private String importantLevel;
    private Date expectFinishDate;
    private Date updatedDate;
    private String taskId;
    private Boolean isFinnished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel;
    }

    public Date getExpectFinishDate() {
        return expectFinishDate;
    }

    public void setExpectFinishDate(Date expectFinishDate) {
        this.expectFinishDate = expectFinishDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getFinnished() {
        return isFinnished;
    }

    public void setFinnished(Boolean finnished) {
        isFinnished = finnished;
    }
}
