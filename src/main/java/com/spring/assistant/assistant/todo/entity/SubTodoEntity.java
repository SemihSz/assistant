package com.spring.assistant.assistant.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SubTodoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SubTodoEntity() {
    }

    public String getSubTodoTitle() {
        return subTodoTitle;
    }

    public SubTodoEntity setSubTodoTitle(String subTodoTitle) {
        this.subTodoTitle = subTodoTitle;
        return this;
    }

    public String getSubTodoDescription() {
        return subTodoDescription;
    }

    public SubTodoEntity setSubTodoDescription(String subTodoDescription) {
        this.subTodoDescription = subTodoDescription;
        return this;
    }

    public String getSubTodoCategory() {
        return subTodoCategory;
    }

    public SubTodoEntity setSubTodoCategory(String subTodoCategory) {
        this.subTodoCategory = subTodoCategory;
        return this;
    }

    public Date getSubTodoCreatedDate() {
        return subTodoCreatedDate;
    }

    public SubTodoEntity setSubTodoCreatedDate(Date subTodoCreatedDate) {
        this.subTodoCreatedDate = subTodoCreatedDate;
        return this;
    }

    public Date getSubTodoFinishDate() {
        return subTodoFinishDate;
    }

    public SubTodoEntity setSubTodoFinishDate(Date subTodoFinishDate) {
        this.subTodoFinishDate = subTodoFinishDate;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(String subTaskId) {
        this.subTaskId = subTaskId;
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
}
