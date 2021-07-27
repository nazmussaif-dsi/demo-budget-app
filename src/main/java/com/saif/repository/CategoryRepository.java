package com.saif.repository;

import com.saif.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {
    private static final List<Category> categoryList = new ArrayList<>();

    public void saveCategory(Category category){
        long id = categoryList.size() + 1;
        category.setId(id);
        categoryList.add(category);
    };

    public Category getCategoryById(Long id){
        for(Category category : categoryList){
            if(category.getId() == id){
                return category;
            }
        }
        return null;
    };

    public List<Category> getAllCategories(){
        return categoryList;
    };

    public void deleteCategory(Long id){
        categoryList.removeIf(category -> category.getId() == id);
    };
}
