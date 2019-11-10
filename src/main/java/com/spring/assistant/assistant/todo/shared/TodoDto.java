package com.spring.assistant.assistant.todo.shared;

import java.time.LocalDate;
import java.util.Date;

public class TodoDto {

    private long id;
    private String title;
    private String description;
    private String category;
    private String userId;
    private LocalDate createdDate;
    private String importantLevel;
    private LocalDate expectFinishDate;
    private boolean isFinnished = false;
    private LocalDate updatedDate;
    private String taskId;

    public TodoDto() {
    }

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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getImportantLevel() {
        return importantLevel;
    }

    public void setImportantLevel(String importantLevel) {
        this.importantLevel = importantLevel;
    }

    public LocalDate getExpectFinishDate() {
        return expectFinishDate;
    }

    public void setExpectFinishDate(LocalDate expectFinishDate) {
        this.expectFinishDate = expectFinishDate;
    }

    public boolean isFinnished() {
        return isFinnished;
    }

    public void setFinnished(boolean finnished) {
        isFinnished = finnished;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
