package com.saif.apis;

import com.saif.model.Category;
import com.saif.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/categories")
public class CategoryRestController {
  private final CategoryService categoryService;

  @GetMapping
  public List<Category> getAllCategories(){
    return categoryService.getAllCategories();
  }

  @GetMapping("/{id}")
  public Category getCategory(@PathVariable("id") Long id){
    return categoryService.getCategoryById(id);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public String addCategory(@RequestBody Category category) {
    categoryService.saveCategory(category);
    return "added";
  }

  @PostMapping("/update")
  public String updateCategory(@RequestBody Category category){
    categoryService.updateCategory(category);
    return "updated";
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable("id") Long id) {
    categoryService.deleteCategory(id);
  }
}