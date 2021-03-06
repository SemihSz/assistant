package com.spring.assistant.assistant.blog.service;

import com.spring.assistant.assistant.blog.entity.DbUploadFileEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("upload")
public interface UploadService {

    void storeFile(MultipartFile multipartFile, String uploadId);

    DbUploadFileEntity getFile(String field);

}
