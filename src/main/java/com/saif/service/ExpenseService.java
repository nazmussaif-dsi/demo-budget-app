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
              expense.getCategory()
          ))
        .collect(Collectors.toList());
  }

  public ExpenseDTO saveExpense(ExpenseDTO expenseDTO) {
    expenseRepository.saveExpense(expenseDtoToExpense(expenseDTO));
    return expenseDTO;
  }

  public ExpenseDTO updateExpense(ExpenseDTO expenseDTO) {
    expenseRepository.updateExpense(expenseDtoToExpense(expenseDTO));
    return expenseDTO;
  }

  public void deleteExpense(Long id) {
    expenseRepository.deleteExpense(id);
  }

  private Expense expenseDtoToExpense(ExpenseDTO expenseDTO) {
    Expense expense = new Expense();
    expense.setId(expenseDTO.getId());
    expense.setExpenseDate(expenseDTO.getExpenseDate());
    expense.setDescription(expenseDTO.getDescription());
    expense.setAmount(expenseDTO.getAmount());
    expense.setUser(userRepository.getUserById(expenseDTO.getUserId()));
    expense.setCategory(expenseDTO.getCategory());

    return expense;
  }

  private ExpenseDTO expenseToExpenseDTO(Expense expense) {
    ExpenseDTO expenseDTO = new ExpenseDTO();
    expenseDTO.setId(expense.getId());
    expenseDTO.setExpenseDate(expense.getExpenseDate());
    expenseDTO.setDescription(expense.getDescription());
    expenseDTO.setAmount(expense.getAmount());
    expenseDTO.setUserId(expense.getUser().getId());
    expenseDTO.setCategory(expense.getCategory());

    return expenseDTO;
  }
}
