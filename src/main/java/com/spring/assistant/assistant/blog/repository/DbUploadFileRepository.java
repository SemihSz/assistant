package com.spring.assistant.assistant.blog.repository;


import com.spring.assistant.assistant.blog.entity.DbUploadFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbUploadFileRepository extends JpaRepository<DbUploadFileEntity, String> {

    Optional<DbUploadFileEntity> findById(String id);

}
