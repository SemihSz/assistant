package com.spring.assistant.assistant.blog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DbUploadFileEntity {

    @Id
    private String id;

    private String userId;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;
}
