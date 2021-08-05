package com.saif.repository;

import com.saif.model.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ExpenseRepository {

  private final HibernateTemplate hibernateTemplate;

  public List<Expense> getAllExpenses() {
    return hibernateTemplate.loadAll(Expense.class);
  }

  public Expense getExpenseById(Long id) {
    return hibernateTemplate.get(Expense.class, id);
  }

  @Transactional
  public void saveExpense(Expense expense) {
    hibernateTemplate.save(expense);
  }

  @Transactional
  public void updateExpense(Expense expense) {
    hibernateTemplate.update(expense);
  }

  @Transactional
  public void deleteExpense(Long id) {
    hibernateTemplate.delete(hibernateTemplate.load(Expense.class, id));
  }
}
