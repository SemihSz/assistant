package com.spring.assistant.assistant.todo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@DynamicUpdate
@NoArgsConstructor
@Setter
@Getter
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

    private boolean isFinished = false;

    private String subTaskId;

    private String userId;

}
