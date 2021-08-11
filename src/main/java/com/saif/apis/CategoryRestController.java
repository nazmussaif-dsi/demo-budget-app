package com.saif.apis;

import com.saif.helper.exception.ServiceException;
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
  public List<Category> findAll(){
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable("id") Long id) throws ServiceException {
    return categoryService.findById(id);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody Category category) {
    categoryService.create(category);
    return "added";
  }

  @PostMapping("/update")
  public String update(@RequestBody Category category){
    categoryService.update(category);
    return "updated";
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    categoryService.delete(id);
  }
}