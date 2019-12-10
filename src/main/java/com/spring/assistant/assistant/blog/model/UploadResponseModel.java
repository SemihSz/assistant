package com.spring.assistant.assistant.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author semih on Aralık, 2019, 13.12.2019, 23:36:34
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UploadResponseModel {

    private String userName;

    private String fileName;

    private String fileType;

    private byte[] data;

    private Date createDate;
}
