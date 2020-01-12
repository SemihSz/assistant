package com.spring.assistant.assistant.todo.service.implemantation;

import com.spring.assistant.assistant.todo.entity.CategoryEntity;
import com.spring.assistant.assistant.todo.model.request.CategoryRequestModel;
import com.spring.assistant.assistant.todo.repository.CategoryRepository;
import com.spring.assistant.assistant.todo.service.CategoryService;
import com.spring.assistant.assistant.todo.shared.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void createNewCategory(CategoryRequestModel categoryRequestModel) {
        final String categoryNmae =categoryRequestModel.getCategoryName().toUpperCase();
        if (!checkCategoryNameLenght(categoryNmae)) throw new RuntimeException("Category Title is short");
        if (controlCategoryNameIsNotExsist(categoryNmae)){
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .categoryName(categoryNmae)
                    .createdDate(LocalDate.now())
                    .build();
            categoryRepository.save(categoryEntity);
        }

    }

    @Override
    public List<CategoryEntity> showCategories() {
        return (List<CategoryEntity>) categoryRepository.findAll();
    }

    private boolean checkCategoryNameLenght(String categoryName){
        return categoryName.length() >= 2;
    }

    @Override
    public boolean controlCategoryNameIsNotExsist(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName) == null;
    }
}
