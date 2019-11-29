package com.spring.assistant.assistant.todo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
public class TodoEntity {

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

    private boolean isFinnished = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedDate;

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "email")
    private String email;


}
