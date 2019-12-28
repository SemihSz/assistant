package com.spring.assistant.assistant.blog.executable.service;

import com.spring.assistant.assistant.blog.entity.DbUploadFileEntity;
import com.spring.assistant.assistant.blog.model.UploadModelInput;
import com.spring.assistant.assistant.blog.repository.DbUploadFileRepository;
import com.spring.assistant.assistant.interfaces.SimpleTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author semih on Aralık, 2019, 13.12.2019, 23:35:31
 */

@Service
@AllArgsConstructor
@Slf4j
public class UploadPostService implements SimpleTask<UploadModelInput, DbUploadFileEntity> {

    private final DbUploadFileRepository dbUploadFileRepository;

    @Override
    public DbUploadFileEntity apply(UploadModelInput uploadModelInput) {

        final DbUploadFileEntity dbUploadFileEntity = DbUploadFileEntity.builder()
                .id(uploadModelInput.getId())
                .userId(uploadModelInput.getUserId())
                .fileName(uploadModelInput.getFileName())
                .fileType(uploadModelInput.getFileType())
                .createDate(uploadModelInput.getCreateDate())
                .data(uploadModelInput.getData())
                .build();


        return dbUploadFileRepository.save(dbUploadFileEntity);
    }
}
