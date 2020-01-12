package com.spring.assistant.assistant.todo.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ImportantType {

    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1");

    @Getter
    @Setter
    public String importantLevelNum;

}
