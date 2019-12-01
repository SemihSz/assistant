package com.spring.assistant.assistant.todo.shared.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public enum PostStatusType {

    NEW("NEW"),
    UPDATE("UPDATE");

    @Getter
    @Setter
    private String postStatus;
}
