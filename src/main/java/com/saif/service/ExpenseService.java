package com.saif.service;

import com.saif.model.Expense;
import com.saif.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExpenseService {
  private final ExpenseRepository expenseRepository;

  public void saveExpense(Expense expense) {
    expenseRepository.saveExpense(expense);
  }

  public Expense getExpenseById(Long id) {
    return expenseRepository.getExpenseById(id);
  }

  public List<Expense> getAllExpenses() {
    return expenseRepository.getAllExpenses();
  }

  public void updateExpense(Expense expense) {
    expenseRepository.updateExpense(expense);
  }

  public void deleteExpense(Long id) {
    expenseRepository.deleteExpense(id);
  }
}
