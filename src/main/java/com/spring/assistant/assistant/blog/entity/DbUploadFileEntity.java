package com.spring.assistant.assistant.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

	private String urlLink;
}
