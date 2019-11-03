package com.spring.assistant.assistant.todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SubTodoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String subTodoTitle;
    private String subTodoDescription;
    private String subTodoCategory;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date subTodocreatedDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date subTodoFinishDate;
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

    public Date getSubTodocreatedDate() {
        return subTodocreatedDate;
    }

    public SubTodoEntity setSubTodocreatedDate(Date subTodocreatedDate) {
        this.subTodocreatedDate = subTodocreatedDate;
        return this;
    }

    public Date getSubTodoFinishDate() {
        return subTodoFinishDate;
    }

    public SubTodoEntity setSubTodoFinishDate(Date subTodoFinishDate) {
        this.subTodoFinishDate = subTodoFinishDate;
        return this;
    }
}
