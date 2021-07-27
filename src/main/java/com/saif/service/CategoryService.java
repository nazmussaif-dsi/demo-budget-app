package com.saif.service;

import com.saif.model.Category;
import com.saif.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void saveCategory(Category category) {
        categoryRepository.saveCategory(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteCategory(id);
    }
}