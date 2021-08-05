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
  public String addExpense(@RequestBody ExpenseDTO expenseDTO) {
    expenseService.saveExpense(expenseDTO);
    return "added";
  }

  @PostMapping("/update")
  public String updateExpense(@RequestBody ExpenseDTO expenseDTO) {
    expenseService.updateExpense(expenseDTO);
    return "updated";
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String deleteExpense(@PathVariable("id") Long id) {
    expenseService.deleteExpense(id);
    return "deleted";
  }
}
