package com.spring.assistant.assistant.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TodoEntity {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private String category;
    private String userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private String importantLevel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date expectFinishDate;
    private Boolean isFinnished = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "task_id")
    private String taskId;


    public TodoEntity() {
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

    public Boolean isFinnished() {
        return isFinnished;
    }

    public void setFinnished(boolean finnished) {
        isFinnished = finnished;
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
}
