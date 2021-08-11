package com.saif.service;

import com.saif.helper.exception.ServiceException;
import com.saif.model.Category;
import com.saif.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public void create(Category category) {
    categoryRepository.save(category);
  }

  public Category findById(Long id) throws ServiceException {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new ServiceException(
            "Category not found with ID: " + id,
            HttpStatus.NOT_FOUND
        ));
  }

  public List<Category> findAll() {
    return categoryRepository.findAll();
  }

  public void update(Category category) {
    categoryRepository.save(category);
  }

  public void delete(Long id) {
    categoryRepository.deleteById(id);
  }
}