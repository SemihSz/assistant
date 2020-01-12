package com.spring.assistant.assistant.todo.repository;

import com.spring.assistant.assistant.todo.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryName(String categoryName);
}
