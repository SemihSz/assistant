package com.spring.assistant.assistant.todo.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum EmailType {

    SEND("SEND"),
    NOTSEND("NOTSEND"),
    NEW("NEW");

    @Getter
    @Setter
    public String emailType;
}
