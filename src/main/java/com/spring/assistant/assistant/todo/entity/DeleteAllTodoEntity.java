package com.spring.assistant.assistant.todo.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteAllTodoEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String description;

    private String category;

    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private String importantLevel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectFinishDate;

    private Boolean isFinnished = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;

    @Column(name = "task_id")
    private String taskId;

    private String subTodoTitle;

    private String subTodoDescription;

    private String subTodoCategory;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate subTodoCreatedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate subTodoFinishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate subTodoUpdateDate;

    private Boolean isFinished = false;

    private String subTaskId;

    private String todoPrefix;




}
