package com.spring.assistant.assistant.blog.repository;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    PostEntity findByCommentId(String commentId);

    List<PostEntity> findByUserId(String userId);

    @Query("SELECT t FROM PostEntity t WHERE t.userId=:userId")
    Page<PostEntity> pages(@Param("userId") String userId, Pageable pageable);

}
