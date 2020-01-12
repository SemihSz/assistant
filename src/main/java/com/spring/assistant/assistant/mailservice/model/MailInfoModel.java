package com.spring.assistant.assistant.mailservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailInfoModel {

    private String todoType;

    private String to;

    private String subject;

    private String body1;

    private String body2;

    private String body3;

    private String body4;

}
