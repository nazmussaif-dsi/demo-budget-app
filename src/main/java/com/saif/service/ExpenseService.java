package com.saif.service;

import com.saif.helper.dtos.ExpenseDTO;
import com.saif.helper.exception.ServiceException;
import com.saif.model.Expense;
import com.saif.repository.ExpenseRepository;
import com.saif.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExpenseService {
  private final ExpenseRepository expenseRepository;
  private final UserRepository userRepository;

  public ExpenseDTO findById(Long id) throws ServiceException {
    return expenseToExpenseDTO(expenseRepository.findById(id)
        .orElseThrow(() -> new ServiceException(
            "Expense not found with ID: " + id,
            HttpStatus.NOT_FOUND
        ))
    );
  }

  public List<ExpenseDTO> findAllByUserId(Long userId) {
    return expenseRepository
        .findByUser_Id(userId)
//        .findAll()
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

  public ExpenseDTO create(ExpenseDTO expenseDTO) throws ServiceException {
    expenseRepository.save(expenseDtoToExpense(expenseDTO));
    return expenseDTO;
  }

  public ExpenseDTO update(ExpenseDTO expenseDTO) throws ServiceException {
    expenseRepository.save(expenseDtoToExpense(expenseDTO));
    return expenseDTO;
  }

  public void delete(Long id) {
    expenseRepository.deleteById(id);
  }

  private Expense expenseDtoToExpense(ExpenseDTO expenseDTO) throws ServiceException {
    Expense expense = new Expense();
    expense.setId(expenseDTO.getId());
    expense.setExpenseDate(expenseDTO.getExpenseDate());
    expense.setDescription(expenseDTO.getDescription());
    expense.setAmount(expenseDTO.getAmount());
    expense.setUser(userRepository.findById(expenseDTO.getUserId())
        .orElseThrow(() -> new ServiceException(
            "User not found with ID: " + expenseDTO.getUserId(),
            HttpStatus.NOT_FOUND
        ))
    );
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
