package com.saif.repository;

import com.saif.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CategoryRepository {
  private final HibernateTemplate hibernateTemplate;

  public List<Category> getAllCategories() {
    return hibernateTemplate.loadAll(Category.class);
  }

  public Category getCategoryById(Long id) {
    return hibernateTemplate.get(Category.class, id);
  }

  @Transactional
  public void saveCategory(Category category) {
    hibernateTemplate.save(category);
  }

  @Transactional
  public void updateCategory(Category category) {
    hibernateTemplate.update(category);
  }

  @Transactional
  public void deleteCategory(Long id) {
    hibernateTemplate.delete(hibernateTemplate.load(Category.class, id));
  }
}
