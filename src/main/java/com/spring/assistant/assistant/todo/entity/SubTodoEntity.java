package com.spring.assistant.assistant.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SubTodoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

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

    private boolean isFinished;

    private String subTaskId;

    private String userId;

}
