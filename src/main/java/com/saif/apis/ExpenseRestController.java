package com.saif.apis;

import com.saif.helper.dtos.ExpenseDTO;
import com.saif.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/expenses")
public class ExpenseRestController {
  private final ExpenseService expenseService;

  @GetMapping
  public List<ExpenseDTO> getAllExpenses() {
    return expenseService.getAllExpenses();
  }

  @GetMapping("/{id}")
  public ExpenseDTO getExpense(@PathVariable("id") Long id) {
    return expenseService.getExpenseById(id);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public ExpenseDTO addExpense(@RequestBody ExpenseDTO expenseDTO) {
    return expenseService.saveExpense(expenseDTO);
  }

  @PostMapping("/update")
  public ExpenseDTO updateExpense(@RequestBody ExpenseDTO expenseDTO) {
    return expenseService.updateExpense(expenseDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteExpense(@PathVariable("id") Long id) {
    expenseService.deleteExpense(id);
  }
}
