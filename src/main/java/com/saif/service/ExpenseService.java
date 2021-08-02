package com.saif.service;

import com.saif.helper.dtos.ExpenseDTO;
import com.saif.model.Expense;
import com.saif.repository.CategoryRepository;
import com.saif.repository.ExpenseRepository;
import com.saif.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExpenseService {
  private final ExpenseRepository expenseRepository;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;

  public void saveExpense(ExpenseDTO expenseDTO) {
    expenseRepository.saveExpense(expenseDtoToExpense(expenseDTO));
  }

  public ExpenseDTO getExpenseById(Long id) {
    return expenseToExpenseDTO(expenseRepository.getExpenseById(id));
  }

  public List<ExpenseDTO> getAllExpenses() {
    return expenseRepository
        .getAllExpenses()
        .stream()
        .map((Expense expense) ->
          new ExpenseDTO(
              expense.getId(),
              expense.getExpenseDate(),
              expense.getAmount(),
              expense.getDescription(),
              expense.getUser().getId(),
              expense.getCategory().getId()
          ))
        .collect(Collectors.toList());
  }

  public void updateExpense(ExpenseDTO expenseDTO) {
    expenseRepository.updateExpense(expenseDtoToExpense(expenseDTO));
  }

  public void deleteExpense(Long id) {
    expenseRepository.deleteExpense(id);
  }

  private Expense expenseDtoToExpense(ExpenseDTO expenseDTO) {
    Expense expense = new Expense();
    expense.setId(expenseDTO.getUserId());
    expense.setExpenseDate(expenseDTO.getExpenseDate());
    expense.setDescription(expenseDTO.getDescription());
    expense.setAmount(expenseDTO.getAmount());
    expense.setUser(userRepository.getUserById(expenseDTO.getUserId()));
    expense.setCategory(categoryRepository.getCategoryById(expenseDTO.getCategoryId()));

    return expense;
  }

  private ExpenseDTO expenseToExpenseDTO(Expense expense) {
    ExpenseDTO expenseDTO = new ExpenseDTO();
    expenseDTO.setId(expense.getId());
    expenseDTO.setExpenseDate(expense.getExpenseDate());
    expenseDTO.setDescription(expense.getDescription());
    expenseDTO.setAmount(expense.getAmount());
    expenseDTO.setUserId(expense.getUser().getId());
    expenseDTO.setCategoryId(expense.getCategory().getId());

    return expenseDTO;
  }
}
