package com.spring.assistant.assistant.todo.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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

}
