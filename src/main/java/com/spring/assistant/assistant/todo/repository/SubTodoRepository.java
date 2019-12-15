package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubTodoRepository extends JpaRepository<SubTodoEntity, Long> {

    List<SubTodoEntity> findByUserId(String userId);
    List<SubTodoEntity> findByTaskId(String taskId);
    SubTodoEntity findBySubTaskId(String subtaskId);
    List<SubTodoEntity> findBySubTaskIdAndUserId(String userId, String subTaskId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE catgag.sub_todo_entity set is_finished= true WHERE sub_task_id=:subTaskId", nativeQuery = true)
    int finishSubTask(@Param("subTaskId") String subTaskId);

}
