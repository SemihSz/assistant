package com.spring.assistant.assistant.todo.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum PageType {

    PAGESIZE(10),
    PAGENO(0);

    @Getter
    @Setter
    Integer pageType;

}
