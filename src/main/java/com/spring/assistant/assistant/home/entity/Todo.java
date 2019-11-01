package com.spring.assistant.assistant.home.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private String category;
    private String userId;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private String importantLevel;
    @Temporal(TemporalType.DATE)
    private Date expectFinishDate;
    private boolean isFinnished = false;
    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    public Todo() {
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

    public boolean isFinnished() {
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
}
