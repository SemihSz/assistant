package com.spring.assistant.assistant.blog.repository;

import com.spring.assistant.assistant.blog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    PostEntity findByCommentId(String commentId);

}
