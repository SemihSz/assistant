package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.SubTodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTodoRepository extends CrudRepository<SubTodoEntity, Long> {


}
