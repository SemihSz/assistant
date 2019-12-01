package com.spring.assistant.assistant.blog.request;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestModel {

    private String title;

    private String body;

    private String attachFile;

}
