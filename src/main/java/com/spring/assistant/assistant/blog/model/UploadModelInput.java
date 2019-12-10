package com.spring.assistant.assistant.blog.model;

import com.spring.assistant.assistant.general.model.BaseModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author semih on Aralık, 2019, 13.12.2019, 23:39:24
 */

@Getter
@Setter
public class UploadModelInput extends BaseModel {

    private String id;

    private String fileName;

    private String fileType;

    private byte[] data;

    private Date createDate;

    @Builder
    public UploadModelInput(@NotNull String userId, String id, String fileName, String fileType, byte[] data, Date createDate) {
        super(userId);
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.createDate = createDate;
    }
}
