package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.DeleteAllTodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeleteAllTodoRepository extends CrudRepository<DeleteAllTodoEntity, Long> {

	List<DeleteAllTodoEntity> findByUserId(String userId);

}
