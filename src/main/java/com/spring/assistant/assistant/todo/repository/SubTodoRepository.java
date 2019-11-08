package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTodoRepository extends CrudRepository<SubTodoEntity, Long> {

    List<SubTodoEntity> findByUserId(String userId);
    List<SubTodoEntity> findByTaskId(String taskId);
    SubTodoEntity findBySubTaskId(String subtaskId);
    List<SubTodoEntity> findBySubTaskIdAndUserId(String userId, String subTaskId);
}
