package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {

    TodoEntity findById(long id);
    List<TodoEntity> findByTaskId(String taskId);
    TodoEntity findByUserId(String userId);

}
