package com.saif.controller;

import com.saif.model.Category;
import com.saif.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping
  public String showAllCategories(Model model) {
    List<Category> categories = categoryService.findAll();
    model.addAttribute("categories", categories);
    return "categories/category_list";
  }

  @GetMapping("/add")
  public String showCategoryForm(Model model) {
    model.addAttribute("category", new Category());
    return "categories/category_form";
  }

  @PostMapping("/add")
  public String addCategory(@ModelAttribute("category") Category category) {
    categoryService.create(category);
    return "redirect:/categories";
  }

  @PatchMapping("/update")
  public String updateCategory(@ModelAttribute("category") Category category){
    categoryService.update(category);
    return "redirect:/categories";
  }

  @DeleteMapping("/delete/{id}")
  public String deleteCategory(@PathVariable("id") Long id) {
    categoryService.delete(id);
    return "redirect:/categories";
  }
}