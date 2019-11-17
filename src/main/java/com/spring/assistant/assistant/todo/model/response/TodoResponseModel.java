package com.spring.assistant.assistant.todo.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TodoResponseModel {

    private long id;

    private String title;

    private String description;

    private String userId;

    private Date createdDate;

    private String importantLevel;

    private Date expectFinishDate;

    private Date updatedDate;

    private String taskId;

}
