package com.spring.assistant.assistant.todo.service;

import com.spring.assistant.assistant.todo.entity.CategoryEntity;
import com.spring.assistant.assistant.todo.model.request.CategoryRequestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    void createNewCategory(CategoryRequestModel categoryRequestModel);
    List<CategoryEntity> showCategories();
    boolean controlCategoryNameIsNotExsist(String categoryName);
}
