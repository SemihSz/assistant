package com.spring.assistant.assistant.blog.service.implemantation;

import com.spring.assistant.assistant.blog.entity.DbUploadFileEntity;
import com.spring.assistant.assistant.blog.executable.service.UploadPostService;
import com.spring.assistant.assistant.blog.model.UploadModelInput;
import com.spring.assistant.assistant.blog.repository.DbUploadFileRepository;
import com.spring.assistant.assistant.blog.service.UploadService;
import com.spring.assistant.assistant.exception.FileStorageException;
import com.spring.assistant.assistant.exception.ResourceNotFoundException;
import com.spring.assistant.assistant.interfaces.service.GetUserIdService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;


@Service
@Slf4j
@Qualifier("upload")
@AllArgsConstructor
public class UploadServiceImpl implements UploadService, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    private final UploadPostService uploadPostService;

    private final DbUploadFileRepository dbUploadFileRepository;

    private final GetUserIdService getUserIdService;


    @Override
    public void storeFile(MultipartFile multipartFile, String uploadId) {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            if (fileName.contains(".."))
                //TODO DB'ye logglama işlemini yap !! sss
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);


            if (controlUploadId(uploadId)) {
                throw new ResourceNotFoundException("This upload id has been included in db.");
            }

            UploadModelInput uploadModelInput = UploadModelInput.builder()
                    .id(uploadId)
                    .userId(getUserIdService.getUserId())
                    .fileName(fileName)
                    .fileType(multipartFile.getContentType())
                    .createDate(new Date())
                    .data(multipartFile.getBytes())
                    .build();

            final DbUploadFileEntity dbUploadFileEntity = uploadPostService.apply(uploadModelInput);

        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new FileStorageException(e.getMessage());

        }

    }

    private boolean controlUploadId(String uploadId) {

        return dbUploadFileRepository.findById(uploadId).isPresent();

    }

    @Override
    public DbUploadFileEntity getFile(String field) {
        return null;
    }
}
