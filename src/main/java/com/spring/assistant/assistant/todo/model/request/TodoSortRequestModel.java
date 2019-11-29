package com.spring.assistant.assistant.todo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoSortRequestModel {

    private String id;

    private String importantLevel;

    private String createdDate;

    private String category;
}
