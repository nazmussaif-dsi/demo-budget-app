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

//    private static final List<Expense> expenseList = new ArrayList<>();
//
//    public void saveExpense(Expense expense) {
//        long id = expenseList.size() + 1;
//        expense.setId(id);
//        expenseList.add(expense);
//    }
//
//    public Expense getExpenseById(Long id) {
//        for (Expense expense : expenseList) {
//            if (expense.getId() == id) {
//                return expense;
//            }
//        }
//        return null;
//    }
//
//    public List<Expense> getAllExpenses() {
//        return expenseList;
//    }
//
//    public void deleteExpense(Long id) { expenseList.removeIf(expense -> expense.getId() == id);}
}
