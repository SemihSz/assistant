package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    TodoEntity findById(long id);

    List<TodoEntity> findByTaskId(String taskId);

    TodoEntity findByUserId(String userId);

    @Query("SELECT t FROM TodoEntity t WHERE t.userId=:userId")
    List<TodoEntity> finds(@Param("userId") String userId);

    @Query("SELECT t FROM TodoEntity t WHERE t.userId=:userId")
    Page<TodoEntity> pages(@Param("userId") String userId, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE TodoEntity t SET t.isFinnished = true, t.updatedDate=:updatedDate, t.email=:email WHERE t.taskId=:taskId")
    int update(@Param("taskId") String taskId,
               @Param("updatedDate") LocalDate updatedDate,
               @Param("email") String email);


}
